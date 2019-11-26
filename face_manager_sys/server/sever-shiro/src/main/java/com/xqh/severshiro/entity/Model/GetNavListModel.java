package com.xqh.severshiro.entity.Model;

import lombok.Data;

import java.util.List;

/**
 * @Author: xqh
 * @Version 1.0
 */
@Data
public class GetNavListModel {

    private String userName;

    private List<NavListBean> navList;

    @Data
    public static class NavListBean {
        private String title;
        private String icon;
        private String spread;
        private String href;
        private List<ChildrenBean> children;

        @Data
        public static class ChildrenBean {

            private String title;
            private String icon;
            private String href;

        }
    }
}
