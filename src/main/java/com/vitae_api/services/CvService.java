package com.vitae_api.services;


import com.vitae_api.dtos.CvDto;
import com.vitae_api.models.Cv;
import com.vitae_api.models.User;
import com.vitae_api.repositories.CvRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CvService {


    @Autowired
    UserService userService;

    @Autowired
    CvRepository cvRepository;


    public Cv createCv(CvDto cvDto){
        Cv cv = new Cv();
        BeanUtils.copyProperties(cvDto, cv);
        User user = userService.findById(cvDto.userId());
        cv.setUser(user);
        return cvRepository.save(cv);
    }

    public String cvToString(MultipartFile file){
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ChatResponseDto.ChoiceDto chatResponse(MultipartFile file) {
        String pdfContent = cvToString(file);
    }





}
