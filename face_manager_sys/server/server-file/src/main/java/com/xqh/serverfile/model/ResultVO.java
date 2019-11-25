package com.xqh.serverfile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResultVO
 *
 * @author yz3702
 * @date 2019/11/18 13:41
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultVO {
    private String imagePath;
    private String fileName;
}
