package kr.ac.hansung.cse.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryForm {

    @NotBlank(message = "카테고리명은 필수 입력 항목입니다.")
    @Size(max = 50, message = "카테고리명은 50자 이하로 입력해 주세요.")
    private String name;
}
