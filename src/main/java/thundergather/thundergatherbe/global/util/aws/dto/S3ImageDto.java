package thundergather.thundergatherbe.global.util.aws.dto;

import lombok.Builder;


@Builder
public record S3ImageDto(
        Long id,
        String url,
        String fileName,
        Long size
) {




}
