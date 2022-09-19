package com.mvp.question.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.mvp.question.models.enums.CoinType;
import com.mvp.question.validations.EnumValidator;
import com.mvp.question.validations.FileSize;
import com.mvp.question.validations.IsValidFileType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionRequest {
    @NotBlank
    @Size(min = 3, max = 255)
    private String title;
    @NotBlank
    @Size(min = 3, max = 2047)
    private String content;
    @FileSize(maxSizeInMB = 50)
    @IsValidFileType
    private MultipartFile img;
    @NotBlank
    private Float reward_amount;
    @NotBlank
    @EnumValidator(enumClass = CoinType.class)
    private CoinType reward_unit;
    private Integer expire_date;
    private Boolean hide_answers;
    @NotBlank
    private String[] tags;
}
