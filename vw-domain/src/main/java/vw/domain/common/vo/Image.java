package vw.domain.common.vo;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image { // 이미지
    private String imageKey; // 이미지 키

    public Image(String imageKey) {
        // imageKey를 받아서 Image 객체를 생성하는 생성자
        this.imageKey = imageKey;
    }

    public static Image valueOf(String imageKey) { // imageKey를 전달받아 Image 객체를 생성해 반환
        return new Image(imageKey);
    }
}
