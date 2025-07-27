package com.vitae_api.services;


import com.vitae_api.dtos.CvDto;
import com.vitae_api.dtos.GeminiCvDto;
import com.vitae_api.dtos.GeminiResponse;
import com.vitae_api.models.Cv;
import com.vitae_api.models.User;
import com.vitae_api.repositories.CvRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CvService {


    @Autowired
    UserService userService;

    @Autowired
    CvRepository cvRepository;

    @Autowired
    GeminiService geminiService;



    public String cvToString(MultipartFile file){
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public List<Cv> listBigGrade(){
        return cvRepository.findCvWithHighestGrade();
    }
    public String generateEvaluationPrompt(String cv) {
        return """
        Avalie o seguinte currículo:

        %s

        Gere uma nota de 0 a 10 com até uma casa decimal, e explique brevemente a razão da nota.
        Responda no seguinte formato:
        Nota: <valor>
        Justificativa: <explicação>
        """.formatted(cv);
    }

    public GeminiCvDto getRevision(String response) {
        Pattern padrao = Pattern.compile("Nota:\\s*(\\d+(\\.\\d+)?)\\s*Justificativa:\\s*(.*)", Pattern.DOTALL);
        Matcher matcher = padrao.matcher(response);

        if (matcher.find()) {
            double nota = Double.parseDouble(matcher.group(1));
            String justify = matcher.group(3).trim();
            return new GeminiCvDto(nota, justify);
        }

        throw new IllegalArgumentException("Formato da resposta do Gemini inválido.");
    }


    public Cv chatResponse(MultipartFile file, UUID userId) {
        String pdfContent = cvToString(file);
        User user = userService.findById(userId);
        String prompt = generateEvaluationPrompt(pdfContent);
        GeminiCvDto geminiCvDto = getRevision(geminiService.generateText(prompt).block());
        Cv cv = new Cv(user, geminiCvDto.grade(), geminiCvDto.justify());
        return cvRepository.save(cv);
    }





}
