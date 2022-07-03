"use strict";
/*
 * ATTENTION: The "eval" devtool has been used (maybe by default in mode: "development").
 * This devtool is neither made for production nor for readable output files.
 * It uses "eval()" calls to create a separate source file in the browser devtools.
 * If you are trying to read the output file, select a different devtool (https://webpack.js.org/configuration/devtool/)
 * or disable the default devtool with "devtool: false".
 * If you are looking for production-ready output files, see mode: "production" (https://webpack.js.org/configuration/mode/).
 */
(self["webpackChunkfrontend"] = self["webpackChunkfrontend"] || []).push([["src_views_Test_vue"],{

/***/ "./node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[0]!./node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/views/Test.vue?vue&type=script&lang=js":
/*!*********************************************************************************************************************************************************************************!*\
  !*** ./node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[0]!./node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/views/Test.vue?vue&type=script&lang=js ***!
  \*********************************************************************************************************************************************************************************/
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {

eval("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var _api_index_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @/api/index.js */ \"./src/api/index.js\");\n\n/* harmony default export */ __webpack_exports__[\"default\"] = ({\n  name: \"Test\",\n\n  setup() {\n    async function submit() {\n      const res = await (0,_api_index_js__WEBPACK_IMPORTED_MODULE_0__.test)();\n      console.log(res);\n    }\n\n    return {\n      submit\n    };\n  }\n\n});\n\n//# sourceURL=webpack://frontend/./src/views/Test.vue?./node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use%5B0%5D!./node_modules/vue-loader/dist/index.js??ruleSet%5B0%5D.use%5B0%5D");

/***/ }),

/***/ "./node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[0]!./node_modules/vue-loader/dist/templateLoader.js??ruleSet[1].rules[3]!./node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/views/Test.vue?vue&type=template&id=5b2d5ecc":
/*!*************************************************************************************************************************************************************************************************************************************************************!*\
  !*** ./node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[0]!./node_modules/vue-loader/dist/templateLoader.js??ruleSet[1].rules[3]!./node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/views/Test.vue?vue&type=template&id=5b2d5ecc ***!
  \*************************************************************************************************************************************************************************************************************************************************************/
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {

eval("__webpack_require__.r(__webpack_exports__);\n/* harmony export */ __webpack_require__.d(__webpack_exports__, {\n/* harmony export */   \"render\": function() { return /* binding */ render; }\n/* harmony export */ });\n/* harmony import */ var vue__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! vue */ \"./node_modules/vue/dist/vue.runtime.esm-bundler.js\");\n\nfunction render(_ctx, _cache, $props, $setup, $data, $options) {\n  return (0,vue__WEBPACK_IMPORTED_MODULE_0__.openBlock)(), (0,vue__WEBPACK_IMPORTED_MODULE_0__.createElementBlock)(\"div\", null, [(0,vue__WEBPACK_IMPORTED_MODULE_0__.createElementVNode)(\"input\", {\n    type: \"password\",\n    onKeyup: _cache[0] || (_cache[0] = (0,vue__WEBPACK_IMPORTED_MODULE_0__.withKeys)((...args) => $setup.submit && $setup.submit(...args), [\"enter\"]))\n  }, null, 32\n  /* HYDRATE_EVENTS */\n  )]);\n}\n\n//# sourceURL=webpack://frontend/./src/views/Test.vue?./node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use%5B0%5D!./node_modules/vue-loader/dist/templateLoader.js??ruleSet%5B1%5D.rules%5B3%5D!./node_modules/vue-loader/dist/index.js??ruleSet%5B0%5D.use%5B0%5D");

/***/ }),

/***/ "./src/api/index.js":
/*!**************************!*\
  !*** ./src/api/index.js ***!
  \**************************/
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {

eval("__webpack_require__.r(__webpack_exports__);\n/* harmony export */ __webpack_require__.d(__webpack_exports__, {\n/* harmony export */   \"findByEmail\": function() { return /* binding */ findByEmail; },\n/* harmony export */   \"login\": function() { return /* binding */ login; },\n/* harmony export */   \"logout\": function() { return /* binding */ logout; },\n/* harmony export */   \"register\": function() { return /* binding */ register; },\n/* harmony export */   \"test\": function() { return /* binding */ test; }\n/* harmony export */ });\n/* harmony import */ var _api_request__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @/api/request */ \"./src/api/request.js\");\n // 註冊\n\nfunction register(data) {\n  return (0,_api_request__WEBPACK_IMPORTED_MODULE_0__[\"default\"])({\n    url: `/user/register`,\n    method: \"post\",\n    data\n  });\n} // 登入\n\nfunction login(data) {\n  return (0,_api_request__WEBPACK_IMPORTED_MODULE_0__[\"default\"])({\n    url: `/user/login`,\n    method: \"post\",\n    data\n  });\n} // 登出\n\nfunction logout() {\n  return (0,_api_request__WEBPACK_IMPORTED_MODULE_0__[\"default\"])({\n    url: `/user/logout`,\n    method: \"post\"\n  });\n} // 管理員測試\n\nfunction findByEmail(data) {\n  return (0,_api_request__WEBPACK_IMPORTED_MODULE_0__[\"default\"])({\n    url: `/admin/findByEmail`,\n    method: \"post\",\n    data\n  });\n} // 管理員測試2\n\nfunction test() {\n  return (0,_api_request__WEBPACK_IMPORTED_MODULE_0__[\"default\"])({\n    url: `/admin/test`,\n    method: \"post\"\n  });\n}\n\n//# sourceURL=webpack://frontend/./src/api/index.js?");

/***/ }),

/***/ "./src/api/request.js":
/*!****************************!*\
  !*** ./src/api/request.js ***!
  \****************************/
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {

eval("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var axios__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! axios */ \"./node_modules/axios/index.js\");\n/* harmony import */ var axios__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(axios__WEBPACK_IMPORTED_MODULE_0__);\n // 創建axios實例\n\nconst api = \"http://localhost:9090/\";\nconst service = axios__WEBPACK_IMPORTED_MODULE_0___default().create({\n  baseURL: api\n  /* api 域名 */\n  ,\n\n  /* headers: { 'Content-Type': 'application/json' }, */\n  timeout: 8000\n  /* api響應時間 */\n  ,\n  withCredentials: false\n}); // 請求攔截器\n\nservice.interceptors.request.use(config => {\n  const token = localStorage.getItem(\"token\");\n  !token ? delete config.headers.Authorization : config.headers.Authorization = 'Bearer ' + token;\n  return config;\n}, error => {\n  return Promise.reject(error);\n});\n/* harmony default export */ __webpack_exports__[\"default\"] = (service);\n\n//# sourceURL=webpack://frontend/./src/api/request.js?");

/***/ }),

/***/ "./src/views/Test.vue":
/*!****************************!*\
  !*** ./src/views/Test.vue ***!
  \****************************/
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {

eval("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var _Test_vue_vue_type_template_id_5b2d5ecc__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./Test.vue?vue&type=template&id=5b2d5ecc */ \"./src/views/Test.vue?vue&type=template&id=5b2d5ecc\");\n/* harmony import */ var _Test_vue_vue_type_script_lang_js__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./Test.vue?vue&type=script&lang=js */ \"./src/views/Test.vue?vue&type=script&lang=js\");\n/* harmony import */ var D_frontend_node_modules_vue_loader_dist_exportHelper_js__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./node_modules/vue-loader/dist/exportHelper.js */ \"./node_modules/vue-loader/dist/exportHelper.js\");\n\n\n\n\n;\nconst __exports__ = /*#__PURE__*/(0,D_frontend_node_modules_vue_loader_dist_exportHelper_js__WEBPACK_IMPORTED_MODULE_2__[\"default\"])(_Test_vue_vue_type_script_lang_js__WEBPACK_IMPORTED_MODULE_1__[\"default\"], [['render',_Test_vue_vue_type_template_id_5b2d5ecc__WEBPACK_IMPORTED_MODULE_0__.render],['__file',\"src/views/Test.vue\"]])\n/* hot reload */\nif (false) {}\n\n\n/* harmony default export */ __webpack_exports__[\"default\"] = (__exports__);\n\n//# sourceURL=webpack://frontend/./src/views/Test.vue?");

/***/ }),

/***/ "./src/views/Test.vue?vue&type=script&lang=js":
/*!****************************************************!*\
  !*** ./src/views/Test.vue?vue&type=script&lang=js ***!
  \****************************************************/
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {

eval("__webpack_require__.r(__webpack_exports__);\n/* harmony export */ __webpack_require__.d(__webpack_exports__, {\n/* harmony export */   \"default\": function() { return /* reexport safe */ _node_modules_babel_loader_lib_index_js_clonedRuleSet_40_use_0_node_modules_vue_loader_dist_index_js_ruleSet_0_use_0_Test_vue_vue_type_script_lang_js__WEBPACK_IMPORTED_MODULE_0__[\"default\"]; }\n/* harmony export */ });\n/* harmony import */ var _node_modules_babel_loader_lib_index_js_clonedRuleSet_40_use_0_node_modules_vue_loader_dist_index_js_ruleSet_0_use_0_Test_vue_vue_type_script_lang_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[0]!../../node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./Test.vue?vue&type=script&lang=js */ \"./node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[0]!./node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/views/Test.vue?vue&type=script&lang=js\");\n \n\n//# sourceURL=webpack://frontend/./src/views/Test.vue?");

/***/ }),

/***/ "./src/views/Test.vue?vue&type=template&id=5b2d5ecc":
/*!**********************************************************!*\
  !*** ./src/views/Test.vue?vue&type=template&id=5b2d5ecc ***!
  \**********************************************************/
/***/ (function(__unused_webpack_module, __webpack_exports__, __webpack_require__) {

eval("__webpack_require__.r(__webpack_exports__);\n/* harmony export */ __webpack_require__.d(__webpack_exports__, {\n/* harmony export */   \"render\": function() { return /* reexport safe */ _node_modules_babel_loader_lib_index_js_clonedRuleSet_40_use_0_node_modules_vue_loader_dist_templateLoader_js_ruleSet_1_rules_3_node_modules_vue_loader_dist_index_js_ruleSet_0_use_0_Test_vue_vue_type_template_id_5b2d5ecc__WEBPACK_IMPORTED_MODULE_0__.render; }\n/* harmony export */ });\n/* harmony import */ var _node_modules_babel_loader_lib_index_js_clonedRuleSet_40_use_0_node_modules_vue_loader_dist_templateLoader_js_ruleSet_1_rules_3_node_modules_vue_loader_dist_index_js_ruleSet_0_use_0_Test_vue_vue_type_template_id_5b2d5ecc__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! -!../../node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[0]!../../node_modules/vue-loader/dist/templateLoader.js??ruleSet[1].rules[3]!../../node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./Test.vue?vue&type=template&id=5b2d5ecc */ \"./node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[0]!./node_modules/vue-loader/dist/templateLoader.js??ruleSet[1].rules[3]!./node_modules/vue-loader/dist/index.js??ruleSet[0].use[0]!./src/views/Test.vue?vue&type=template&id=5b2d5ecc\");\n\n\n//# sourceURL=webpack://frontend/./src/views/Test.vue?");

/***/ })

}]);