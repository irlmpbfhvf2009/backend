(function(){"use strict";var e={7056:function(e,t,r){var n=r(9242),a=r(3396);const o={id:"app"};function l(e,t,r,n,l,u){const i=(0,a.up)("router-view");return(0,a.wg)(),(0,a.iD)("div",o,[(0,a.Wm)(i)])}var u=r(7139);const i={class:"hello"},s=(0,a.uE)('<p data-v-b9167eee> For a guide and recipes on how to configure / customize this project,<br data-v-b9167eee> check out the <a href="https://cli.vuejs.org" target="_blank" rel="noopener" data-v-b9167eee>vue-cli documentation</a>. </p><h3 data-v-b9167eee>Installed CLI Plugins</h3><ul data-v-b9167eee><li data-v-b9167eee><a href="https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-babel" target="_blank" rel="noopener" data-v-b9167eee>babel</a></li><li data-v-b9167eee><a href="https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-eslint" target="_blank" rel="noopener" data-v-b9167eee>eslint</a></li></ul><h3 data-v-b9167eee>Essential Links</h3><ul data-v-b9167eee><li data-v-b9167eee><a href="https://vuejs.org" target="_blank" rel="noopener" data-v-b9167eee>Core Docs</a></li><li data-v-b9167eee><a href="https://forum.vuejs.org" target="_blank" rel="noopener" data-v-b9167eee>Forum</a></li><li data-v-b9167eee><a href="https://chat.vuejs.org" target="_blank" rel="noopener" data-v-b9167eee>Community Chat</a></li><li data-v-b9167eee><a href="https://twitter.com/vuejs" target="_blank" rel="noopener" data-v-b9167eee>Twitter</a></li><li data-v-b9167eee><a href="https://news.vuejs.org" target="_blank" rel="noopener" data-v-b9167eee>News</a></li></ul><h3 data-v-b9167eee>Ecosystem</h3><ul data-v-b9167eee><li data-v-b9167eee><a href="https://router.vuejs.org" target="_blank" rel="noopener" data-v-b9167eee>vue-router</a></li><li data-v-b9167eee><a href="https://vuex.vuejs.org" target="_blank" rel="noopener" data-v-b9167eee>vuex</a></li><li data-v-b9167eee><a href="https://github.com/vuejs/vue-devtools#vue-devtools" target="_blank" rel="noopener" data-v-b9167eee>vue-devtools</a></li><li data-v-b9167eee><a href="https://vue-loader.vuejs.org" target="_blank" rel="noopener" data-v-b9167eee>vue-loader</a></li><li data-v-b9167eee><a href="https://github.com/vuejs/awesome-vue" target="_blank" rel="noopener" data-v-b9167eee>awesome-vue</a></li></ul>',7);function d(e,t,r,n,o,l){return(0,a.wg)(),(0,a.iD)("div",i,[(0,a._)("h1",null,(0,u.zw)(r.msg),1),s])}var p={name:"HelloWorld",props:{msg:String}},c=r(89);const v=(0,c.Z)(p,[["render",d],["__scopeId","data-v-b9167eee"]]);var h=v,b=r(5408);const f=(0,a._)("h1",null,"註冊",-1),g=(0,a._)("button",null,"確認",-1);function m(e,t,r,o,l,u){return(0,a.wg)(),(0,a.iD)("div",null,[(0,a._)("form",{onSubmit:t[3]||(t[3]=(0,n.iM)((e=>u.register()),["prevent"]))},[f,(0,a.wy)((0,a._)("input",{type:"text",class:"123","onUpdate:modelValue":t[0]||(t[0]=e=>l.user.username=e),placeholder:"請輸入用户名"},null,512),[[n.nr,l.user.username]]),(0,a.wy)((0,a._)("input",{type:"text","onUpdate:modelValue":t[1]||(t[1]=e=>l.user.email=e),placeholder:"請輸入信箱"},null,512),[[n.nr,l.user.email]]),(0,a.wy)((0,a._)("input",{type:"password","onUpdate:modelValue":t[2]||(t[2]=e=>l.user.password=e),placeholder:"請輸入8位密碼"},null,512),[[n.nr,l.user.password]]),g],32)])}var w={name:"Register",data(){return{user:{username:"",password:"",email:""}}},methods:{register(){this.axios.post("api/register",this.user).then((e=>{alert(e.data)})).catch((e=>{alert(e.response.data)}))}}};const _=(0,c.Z)(w,[["render",m]]);var y=_,k={name:"App",components:{HelloWorld:h,Login:b["default"],Register:y}};const j=(0,c.Z)(k,[["render",l]]);var O=j,x=r(678);const S=[{path:"/",name:"Login",component:()=>Promise.resolve().then(r.bind(r,5408))},{path:"/Register",name:"Register",component:y},{path:"/HelloWorld",name:"HelloWorld",component:h}],C=(0,x.p7)({history:(0,x.PO)(),routes:S});var P=C,E=r(6265),U=r.n(E),M=r(6423);const D=(0,n.ri)(O);D.use(M.Z,U()),D.use(P),D.mount("#app")},5408:function(e,t,r){r.r(t),r.d(t,{default:function(){return p}});var n=r(3396),a=r(9242);const o=(0,n._)("h1",null,"登入",-1),l=(0,n.Uk)("   ");function u(e,t,r,u,i,s){return(0,n.wg)(),(0,n.iD)("div",null,[o,(0,n.wy)((0,n._)("input",{type:"text","onUpdate:modelValue":t[0]||(t[0]=e=>i.user.email=e),placeholder:"請輸入信箱"},null,512),[[a.nr,i.user.email]]),(0,n.wy)((0,n._)("input",{type:"password","onUpdate:modelValue":t[1]||(t[1]=e=>i.user.password=e),placeholder:"請輸入8位密碼"},null,512),[[a.nr,i.user.password]]),(0,n._)("button",{onClick:t[2]||(t[2]=(...e)=>s.login&&s.login(...e))},"登入"),l,(0,n._)("button",{onClick:t[3]||(t[3]=(...e)=>s.register&&s.register(...e))},"註冊")])}var i={name:"Login",data(){return{user:{email:"",password:""}}},methods:{login(){this.axios.post("api/login",this.user).then((e=>{alert(e.data)})).catch((e=>{alert(e.response.data)}))},register(){this.$router.push({path:"api/register"})}}},s=r(89);const d=(0,s.Z)(i,[["render",u]]);var p=d}},t={};function r(n){var a=t[n];if(void 0!==a)return a.exports;var o=t[n]={id:n,loaded:!1,exports:{}};return e[n](o,o.exports,r),o.loaded=!0,o.exports}r.m=e,function(){r.amdO={}}(),function(){var e=[];r.O=function(t,n,a,o){if(!n){var l=1/0;for(d=0;d<e.length;d++){n=e[d][0],a=e[d][1],o=e[d][2];for(var u=!0,i=0;i<n.length;i++)(!1&o||l>=o)&&Object.keys(r.O).every((function(e){return r.O[e](n[i])}))?n.splice(i--,1):(u=!1,o<l&&(l=o));if(u){e.splice(d--,1);var s=a();void 0!==s&&(t=s)}}return t}o=o||0;for(var d=e.length;d>0&&e[d-1][2]>o;d--)e[d]=e[d-1];e[d]=[n,a,o]}}(),function(){r.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return r.d(t,{a:t}),t}}(),function(){r.d=function(e,t){for(var n in t)r.o(t,n)&&!r.o(e,n)&&Object.defineProperty(e,n,{enumerable:!0,get:t[n]})}}(),function(){r.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){r.hmd=function(e){return e=Object.create(e),e.children||(e.children=[]),Object.defineProperty(e,"exports",{enumerable:!0,set:function(){throw new Error("ES Modules may not assign module.exports or exports.*, Use ESM export syntax, instead: "+e.id)}}),e}}(),function(){r.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){r.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})}}(),function(){var e={143:0};r.O.j=function(t){return 0===e[t]};var t=function(t,n){var a,o,l=n[0],u=n[1],i=n[2],s=0;if(l.some((function(t){return 0!==e[t]}))){for(a in u)r.o(u,a)&&(r.m[a]=u[a]);if(i)var d=i(r)}for(t&&t(n);s<l.length;s++)o=l[s],r.o(e,o)&&e[o]&&e[o][0](),e[o]=0;return r.O(d)},n=self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[];n.forEach(t.bind(null,0)),n.push=t.bind(null,n.push.bind(n))}();var n=r.O(void 0,[998],(function(){return r(7056)}));n=r.O(n)})();
//# sourceMappingURL=app.9855ae0f.js.map