package com.sk.lgtea.module.home.network.response;

import java.util.List;

/**
 * Created by Administrator on 2017/12/28.
 */

public class YitijiaoObj {

    /**
     * add_time : 12月26日
     * operation_submit : [{"head_portrait":"http://121.40.186.118:1554/upload/201712/25/171225115632924738.jpg","name":"咯咯咯咯","add_time":"13:42","content":"不要","operation_submit":[{"images":"http://121.40.186.118:1554/upload/201712/26/171226134209330628.jpg"},{"images":"http://121.40.186.118:1554/upload/201712/26/171226134232916538.jpg"},{"images":"http://121.40.186.118:1554/upload/201712/26/171226134226078057.jpg"}]}]
     */

    private String add_time;
    private List<OperationSubmitBeanX> operation_submit;

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public List<OperationSubmitBeanX> getOperation_submit() {
        return operation_submit;
    }

    public void setOperation_submit(List<OperationSubmitBeanX> operation_submit) {
        this.operation_submit = operation_submit;
    }

    public static class OperationSubmitBeanX {
        /**
         * head_portrait : http://121.40.186.118:1554/upload/201712/25/171225115632924738.jpg
         * name : 咯咯咯咯
         * add_time : 13:42
         * content : 不要
         * operation_submit : [{"images":"http://121.40.186.118:1554/upload/201712/26/171226134209330628.jpg"},{"images":"http://121.40.186.118:1554/upload/201712/26/171226134232916538.jpg"},{"images":"http://121.40.186.118:1554/upload/201712/26/171226134226078057.jpg"}]
         */

        private String head_portrait;
        private String name;
        private String add_time;
        private String content;
        private List<OperationSubmitBean> operation_submit;

        public String getHead_portrait() {
            return head_portrait;
        }

        public void setHead_portrait(String head_portrait) {
            this.head_portrait = head_portrait;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<OperationSubmitBean> getOperation_submit() {
            return operation_submit;
        }

        public void setOperation_submit(List<OperationSubmitBean> operation_submit) {
            this.operation_submit = operation_submit;
        }

        public static class OperationSubmitBean {
            /**
             * id : 1
             * images : http://121.40.186.118:1554/upload/201712/26/171226134209330628.jpg
             */

            private String id;
            private String images;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }
        }
    }
}
