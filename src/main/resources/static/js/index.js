new Vue({
    el: '#app',
    data() {
        return {
            form :{
                name:'',
                password:'',
                code:''
            }
        }
    },

    //初始化
    mounted: function () {
    },
    //方法事件
    methods: {
        load() {
            $("#yzm_img").attr('src', "/kunyueye/url?t="+Math.random());

        },
        /**
         * 校验字段是否为空
         */
         checkNull(name,msg){
            this.setMsg(name,"")
                var v = document.getElementsByName(name)[0].value;
                if(v == ""){
                    this.setMsg(name,msg)
                    return false;
                }
                return true;
            },
            /**
             * 为输入项设置提示消息
             */
             setMsg(name,msg){
                var span = document.getElementById(name+"_msg");
                span.innerHTML="<font color='red'>"+msg+"</font>";
            },
            /**
             * 点击更换验证码
             */
             changeYZM(){
                this.load();
            },
        //登录方法
        loginpage () {
            var form = this.form;
            var newthis = this;
            var d = {
                'name': form.name,
                'password': form.password,
                'code': form.code
            };
            var url = '/kunyueye/loginsubmit';
            $.ajax({
                type: 'post',
                url: url,
                dataType: 'text',
                data: d,
                success: function (res) {
                    if ("yzmerrornull"==res) {
                        $("#error").prepend("<span style=\"color: #f11259; font-size: 12px;\"><strong>验证码失效</strong></span>");
                    } else if ("yzmerror"==res) {
                        $("#error").prepend("<span style=\"color: #f11259; font-size: 12px;\"><strong>验证码输入有误</strong></span>");
                    } else if (res=="error") {
                        $("#error").prepend("<span style=\"color: #f11259; font-size: 12px;\"><strong>用户名或者密码有误</strong></span>");
                    } else {
                        window.location.href=urls+"/kunyueye/shouye";
                        //window.location.href=urls+"/kunyueye/shouye";
                    }
                },
                error: function () {

                }
            });

        },
        yzm(){
            document.getElementById('error').innerHTML = "";
        },
        passwordclik(){
            document.getElementById('error').innerHTML = "";
        },
        nameclik () {
            document.getElementById('error').innerHTML = "";
        }
    }
})