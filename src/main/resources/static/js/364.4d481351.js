"use strict";(self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[]).push([[364],{5101:function(e,t,n){n.d(t,{mY:function(){return c},x4:function(){return s}});var r=n(6265),u=n.n(r);const o="https://limitless-hamlet.herokuapp.com/",a=u().create({baseURL:o,timeout:8e3});a.interceptors.request.use((e=>{const t=localStorage.getItem("token");return console.log(t),t?e.headers.Authorization=t:delete e.headers.Authorization,e}),(e=>Promise.reject(e)));var i=a;function s(e){return i({url:"/user/login",method:"post",data:e})}function c(e){return i({url:"/admin/findByEmail",method:"post",data:e})}},3364:function(e,t,n){n.r(t),n.d(t,{default:function(){return d}});var r=n(3396),u=n(9242);function o(e,t,n,o,a,i){return(0,r.wy)(((0,r.wg)(),(0,r.iD)("input",{type:"text",placeholder:"請輸入信箱","onUpdate:modelValue":t[0]||(t[0]=e=>o.user.email=e),onKeyup:t[1]||(t[1]=(0,u.D2)(((...e)=>o.submit&&o.submit(...e)),["enter"]))},null,544)),[[u.nr,o.user.email]])}var a=n(4870),i=n(5101),s={name:"Admin",setup(){const e=(0,a.qj)({email:""});async function t(){const t=await(0,i.mY)(e);alert(t.data.body)}return{submit:t,user:e}}},c=n(89);const l=(0,c.Z)(s,[["render",o]]);var d=l}}]);
//# sourceMappingURL=364.4d481351.js.map