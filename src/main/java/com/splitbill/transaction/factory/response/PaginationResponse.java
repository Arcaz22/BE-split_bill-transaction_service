package com.splitbill.transaction.factory.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse<T> {
    private List<T> content;
    private Pageable pageable;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pageable {
        private long totalItems;
        private int totalPages;
        private int currentPage;
        private int pageSize;
    }

    public static <T> PaginationResponse<T> of(List<T> content, long totalItems, int page, int size) {
        int totalPages = (int) Math.ceil((double) totalItems / size);

        return PaginationResponse.<T>builder()
                .content(content)
                .pageable(Pageable.builder()
                        .totalItems(totalItems)
                        .totalPages(totalPages)
                        .currentPage(page)
                        .pageSize(size)
                        .build())
                .build();
    }
}
