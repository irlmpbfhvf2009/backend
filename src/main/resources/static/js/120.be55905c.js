"use strict";(self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[]).push([[120],{6120:function(e,t,n){n.r(t),n.d(t,{default:function(){return c}});var r=n(3396),l=n(9242);const o=(0,r._)("h1",null,"登入",-1),s=(0,r.Uk)("   "),u=(0,r._)("button",null,"註冊",-1);function a(e,t,n,a,d,i){const p=(0,r.up)("router-link");return(0,r.wg)(),(0,r.iD)("div",null,[o,(0,r.wy)((0,r._)("input",{type:"text","onUpdate:modelValue":t[0]||(t[0]=e=>d.user.email=e),placeholder:"請輸入信箱"},null,512),[[l.nr,d.user.email]]),(0,r.wy)((0,r._)("input",{type:"password","onUpdate:modelValue":t[1]||(t[1]=e=>d.user.password=e),placeholder:"請輸入8位密碼"},null,512),[[l.nr,d.user.password]]),(0,r._)("button",{onClick:t[2]||(t[2]=(...e)=>i.login&&i.login(...e))},"登入"),s,(0,r.Wm)(p,{to:"/register"},{default:(0,r.w5)((()=>[u])),_:1})])}var d={data(){return{user:{email:"",password:""}}},methods:{login(){this.axios.post("http://localhost:9090/user/login",this.user).then((e=>{alert(e.data.body)})).catch((e=>{alert(e.response.data.body)}))}}},i=n(89);const p=(0,i.Z)(d,[["render",a]]);var c=p}}]);
//# sourceMappingURL=120.be55905c.js.map