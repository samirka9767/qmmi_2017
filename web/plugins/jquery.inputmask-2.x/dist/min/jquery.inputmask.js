/*
 Input Mask plugin for jquery
 http://github.com/RobinHerbots/jquery.inputmask
 Copyright (c) 2010 - 2014 Robin Herbots
 Licensed under the MIT license (http://www.opensource.org/licenses/mit-license.php)
 Version: 2.4.26
*/
(function(d){if(void 0===d.fn.inputmask){var y=function(d){var h=document.createElement("input");d="on"+d;var a=d in h;a||(h.setAttribute(d,"return;"),a="function"==typeof h[d]);return a},D=function(e,h,a){return(e=a.aliases[e])?(e.alias&&D(e.alias,void 0,a),d.extend(!0,a,e),d.extend(!0,a,h),!0):!1},E=function(e){function h(a){e.numericInput&&(a=a.split("").reverse().join(""));var f=!1,h=0,n=e.greedy,p=e.repeat;"*"==p&&(n=!1);1==a.length&&!1==n&&0!=p&&(e.placeholder="");a=d.map(a.split(""),function(a,
d){var k=[];if(a==e.escapeChar)f=!0;else if(a!=e.optionalmarker.start&&a!=e.optionalmarker.end||f){var r=e.definitions[a];if(r&&!f)for(var p=0;p<r.cardinality;p++)k.push(e.placeholder.charAt((h+p)%e.placeholder.length));else k.push(a),f=!1;h+=k.length;return k}});for(var s=a.slice(),r=1;r<p&&n;r++)s=s.concat(a.slice());return{mask:s,repeat:p,greedy:n}}function a(a){e.numericInput&&(a=a.split("").reverse().join(""));var f=!1,h=!1,p=!1;return d.map(a.split(""),function(a,d){var k=[];if(a==e.escapeChar)h=
!0;else{if(a!=e.optionalmarker.start||h){if(a!=e.optionalmarker.end||h){var m=e.definitions[a];if(m&&!h){for(var u=m.prevalidator,G=u?u.length:0,y=1;y<m.cardinality;y++){var C=G>=y?u[y-1]:[],w=C.validator,C=C.cardinality;k.push({fn:w?"string"==typeof w?RegExp(w):new function(){this.test=w}:/./,cardinality:C?C:1,optionality:f,newBlockMarker:!0==f?p:!1,offset:0,casing:m.casing,def:m.definitionSymbol||a});!0==f&&(p=!1)}k.push({fn:m.validator?"string"==typeof m.validator?RegExp(m.validator):new function(){this.test=
m.validator}:/./,cardinality:m.cardinality,optionality:f,newBlockMarker:p,offset:0,casing:m.casing,def:m.definitionSymbol||a})}else k.push({fn:null,cardinality:0,optionality:f,newBlockMarker:p,offset:0,casing:null,def:a}),h=!1;p=!1;return k}f=!1}else f=!0;p=!0}})}function m(a){for(var d=a.length,f=0;f<d&&a.charAt(f)!=e.optionalmarker.start;f++);var h=[a.substring(0,f)];f<d&&h.push(a.substring(f+1,d));return h}function f(k,t,y){for(var n=0,w=0,s=t.length,r=0;r<s&&!(t.charAt(r)==e.optionalmarker.start&&
n++,t.charAt(r)==e.optionalmarker.end&&w++,0<n&&n==w);r++);n=[t.substring(0,r)];r<s&&n.push(t.substring(r+1,s));r=m(n[0]);1<r.length?(t=k+r[0]+(e.optionalmarker.start+r[1]+e.optionalmarker.end)+(1<n.length?n[1]:""),-1==d.inArray(t,p)&&""!=t&&(p.push(t),s=h(t),u.push({mask:t,_buffer:s.mask,buffer:s.mask.slice(),tests:a(t),lastValidPosition:-1,greedy:s.greedy,repeat:s.repeat,metadata:y})),t=k+r[0]+(1<n.length?n[1]:""),-1==d.inArray(t,p)&&""!=t&&(p.push(t),s=h(t),u.push({mask:t,_buffer:s.mask,buffer:s.mask.slice(),
tests:a(t),lastValidPosition:-1,greedy:s.greedy,repeat:s.repeat,metadata:y})),1<m(r[1]).length&&f(k+r[0],r[1]+n[1],y),1<n.length&&1<m(n[1]).length&&(f(k+r[0]+(e.optionalmarker.start+r[1]+e.optionalmarker.end),n[1],y),f(k+r[0],n[1],y))):(t=k+n,-1==d.inArray(t,p)&&""!=t&&(p.push(t),s=h(t),u.push({mask:t,_buffer:s.mask,buffer:s.mask.slice(),tests:a(t),lastValidPosition:-1,greedy:s.greedy,repeat:s.repeat,metadata:y})))}var u=[],p=[];d.isFunction(e.mask)&&(e.mask=e.mask.call(this,e));d.isArray(e.mask)?
d.each(e.mask,function(a,d){void 0!=d.mask?f("",d.mask.toString(),d):f("",d.toString())}):f("",e.mask.toString());return e.greedy?u:u.sort(function(a,d){return a.mask.length-d.mask.length})},ea="function"===typeof ScriptEngineMajorVersion?ScriptEngineMajorVersion():10<=(new Function("/*@cc_on return @_jscript_version; @*/"))(),fa=null!==navigator.userAgent.match(/iphone/i),ga=null!==navigator.userAgent.match(/android.*safari.*/i),R=null!==navigator.userAgent.match(/android.*chrome.*/i),ha=y("paste")?
"paste":y("input")?"input":"propertychange",W=!1,X=!1,Y=!1;R&&(y=navigator.userAgent.match(/chrome.*/i),y=parseInt(RegExp(/[0-9]+/).exec(y)),W=32==y,X=18==y,Y=29==y);var w=function(e,h,a,m){function f(){return e[h]}function u(){return f().tests}function p(){return f()._buffer}function k(){return f().buffer}function t(l,c,b){function g(l,c,b,g){for(var d=w(l),f=b?1:0,v="",A=c.buffer,e=c.tests[d].cardinality;e>f;e--)v+=G(A,d-(e-1));b&&(v+=b);return null!=c.tests[d].fn?c.tests[d].fn.test(v,A,l,g,a):
b==G(c._buffer.slice(),l,!0)||b==a.skipOptionalPartCharacter?{refresh:!0,c:G(c._buffer.slice(),l,!0),pos:l}:!1}if(b=!0===b){var A=g(l,f(),c,b);!0===A&&(A={pos:l});return A}var v=[],A=!1,L=h,p=k().slice(),m=f().lastValidPosition;H(l);var t=[];d.each(e,function(a,d){if("object"==typeof d){h=a;var e=l,x=f().lastValidPosition,q;if(x==m){if(1<e-m)for(x=-1==x?0:x;x<e&&(q=g(x,f(),p[x],!0),!1!==q);x++)I(k(),x,p[x],!0),!0===q&&(q={pos:x}),q=q.pos||x,f().lastValidPosition<q&&(f().lastValidPosition=q);if(!n(e)&&
!g(e,f(),c,b)){x=r(e)-e;for(q=0;q<x&&!1===g(++e,f(),c,b);q++);t.push(h)}}(f().lastValidPosition>=m||h==L)&&0<=e&&e<s()&&(A=g(e,f(),c,b),!1!==A&&(!0===A&&(A={pos:e}),q=A.pos||e,f().lastValidPosition<q&&(f().lastValidPosition=q)),v.push({activeMasksetIndex:a,result:A}))}});h=L;return function(a,b){var f=!1;d.each(b,function(l,b){if(f=-1==d.inArray(b.activeMasksetIndex,a)&&!1!==b.result)return!1});if(f)b=d.map(b,function(l,b){if(-1==d.inArray(l.activeMasksetIndex,a))return l;e[l.activeMasksetIndex].lastValidPosition=
m});else{var v=-1,A=-1,k;d.each(b,function(l,b){-1!=d.inArray(b.activeMasksetIndex,a)&&!1!==b.result&(-1==v||v>b.result.pos)&&(v=b.result.pos,A=b.activeMasksetIndex)});b=d.map(b,function(b,f){if(-1!=d.inArray(b.activeMasksetIndex,a)){if(b.result.pos==v)return b;if(!1!==b.result){for(var L=l;L<v;L++)if(k=g(L,e[b.activeMasksetIndex],e[A].buffer[L],!0),!1===k){e[b.activeMasksetIndex].lastValidPosition=v-1;break}else I(e[b.activeMasksetIndex].buffer,L,e[A].buffer[L],!0),e[b.activeMasksetIndex].lastValidPosition=
L;k=g(v,e[b.activeMasksetIndex],c,!0);!1!==k&&(I(e[b.activeMasksetIndex].buffer,v,c,!0),e[b.activeMasksetIndex].lastValidPosition=v);return b}}})}return b}(t,v)}function y(){var a=h,c={activeMasksetIndex:0,lastValidPosition:-1,next:-1};d.each(e,function(a,l){"object"==typeof l&&(h=a,f().lastValidPosition>c.lastValidPosition?(c.activeMasksetIndex=a,c.lastValidPosition=f().lastValidPosition,c.next=r(f().lastValidPosition)):f().lastValidPosition==c.lastValidPosition&&(-1==c.next||c.next>r(f().lastValidPosition))&&
(c.activeMasksetIndex=a,c.lastValidPosition=f().lastValidPosition,c.next=r(f().lastValidPosition)))});h=-1!=c.lastValidPosition&&e[a].lastValidPosition==c.lastValidPosition?a:c.activeMasksetIndex;a!=h&&(E(k(),r(c.lastValidPosition),s()),f().writeOutBuffer=!0);q.data("_inputmask").activeMasksetIndex=h}function n(a){a=w(a);a=u()[a];return void 0!=a?a.fn:!1}function w(a){return a%u().length}function s(){return a.getMaskLength(p(),f().greedy,f().repeat,k(),a)}function r(a){var c=s();if(a>=c)return c;
for(;++a<c&&!n(a););return a}function H(a){if(0>=a)return 0;for(;0<--a&&!n(a););return a}function I(a,c,b,g){g&&(c=D(a,c));g=u()[w(c)];var d=b;if(void 0!=d&&void 0!=g)switch(g.casing){case "upper":d=b.toUpperCase();break;case "lower":d=b.toLowerCase()}a[c]=d}function G(a,c,b){b&&(c=D(a,c));return a[c]}function D(a,c){for(var b;void 0==a[c]&&a.length<s();)for(b=0;void 0!==p()[b];)a.push(p()[b++]);return c}function C(a,c,b){a._valueSet(c.join(""));void 0!=b&&z(a,b)}function E(a,c,b,g){for(var d=s();c<
b&&c<d;c++)!0===g?n(c)||I(a,c,""):I(a,c,G(p().slice(),c,!0))}function Q(a,c){var b=w(c);I(a,c,G(p(),b))}function N(l){return a.placeholder.charAt(l%a.placeholder.length)}function J(a,c,b,g,A){g=void 0!=g?g.slice():S(a._valueGet()).split("");d.each(e,function(a,b){"object"==typeof b&&(b.buffer=b._buffer.slice(),b.lastValidPosition=-1,b.p=-1)});!0!==b&&(h=0);c&&a._valueSet("");s();d.each(g,function(g,e){if(!0===A){var k=f().p,k=-1==k?k:H(k),h=-1==k?g:r(k);-1==d.inArray(e,p().slice(k+1,h))&&T.call(a,
void 0,!0,e.charCodeAt(0),c,b,g)}else T.call(a,void 0,!0,e.charCodeAt(0),c,b,g)});!0===b&&-1!=f().p&&(f().lastValidPosition=H(f().p))}function R(a){return d.inputmask.escapeRegex.call(this,a)}function S(a){return a.replace(RegExp("("+R(p().join(""))+")*$"),"")}function U(a){var c=k(),b=c.slice(),g,d;for(d=b.length-1;0<=d;d--)if(g=w(d),u()[g].optionality)if(n(d)&&t(d,c[d],!0))break;else b.pop();else break;C(a,b)}function ia(l,c){if(!u()||!0!==c&&l.hasClass("hasDatepicker"))return l[0]._valueGet();
var b=d.map(k(),function(a,b){return n(b)&&t(b,a,!0)?a:null}),b=(B?b.reverse():b).join("");return void 0!=a.onUnMask?a.onUnMask.call(this,k().join(""),b):b}function K(d){!B||"number"!=typeof d||a.greedy&&""==a.placeholder||(d=k().length-d);return d}function z(l,c,b){var g=l.jquery&&0<l.length?l[0]:l;if("number"==typeof c)c=K(c),b=K(b),d(l).is(":visible")&&(b="number"==typeof b?b:c,g.scrollLeft=g.scrollWidth,!1==a.insertMode&&c==b&&b++,g.setSelectionRange?(g.selectionStart=c,g.selectionEnd=ga?c:b):
g.createTextRange&&(l=g.createTextRange(),l.collapse(!0),l.moveEnd("character",b),l.moveStart("character",c),l.select()));else{if(!d(l).is(":visible"))return{begin:0,end:0};g.setSelectionRange?(c=g.selectionStart,b=g.selectionEnd):document.selection&&document.selection.createRange&&(l=document.selection.createRange(),c=0-l.duplicate().moveStart("character",-1E5),b=c+l.text.length);c=K(c);b=K(b);return{begin:c,end:b}}}function O(l){if("*"!=a.repeat){var c=!1,b=0,g=h;d.each(e,function(a,d){if("object"==
typeof d){h=a;var g=H(s());if(d.lastValidPosition>=b&&d.lastValidPosition==g){for(var f=!0,e=0;e<=g;e++){var k=n(e),m=w(e);if(k&&(void 0==l[e]||l[e]==N(e))||!k&&l[e]!=p()[m]){f=!1;break}}if(c=c||f)return!1}b=d.lastValidPosition}});h=g;return c}}function ja(a){a=d._data(a).events;d.each(a,function(a,b){d.each(b,function(a,b){if("inputmask"==b.namespace&&"setvalue"!=b.type){var d=b.handler;b.handler=function(a){if(this.readOnly||this.disabled)a.preventDefault;else return d.apply(this,arguments)}}})})}
function ka(a){function c(a){if(void 0==d.valHooks[a]||!0!=d.valHooks[a].inputmaskpatch){var b=d.valHooks[a]&&d.valHooks[a].get?d.valHooks[a].get:function(a){return a.value},c=d.valHooks[a]&&d.valHooks[a].set?d.valHooks[a].set:function(a,b){a.value=b;return a};d.valHooks[a]={get:function(a){var c=d(a);if(c.data("_inputmask")){if(c.data("_inputmask").opts.autoUnmask)return c.inputmask("unmaskedvalue");a=b(a);c=c.data("_inputmask");return a!=c.masksets[c.activeMasksetIndex]._buffer.join("")?a:""}return b(a)},
set:function(a,b){var g=d(a),f=c(a,b);g.data("_inputmask")&&g.triggerHandler("setvalue.inputmask");return f},inputmaskpatch:!0}}}var b;Object.getOwnPropertyDescriptor&&(b=Object.getOwnPropertyDescriptor(a,"value"));if(b&&b.get){if(!a._valueGet){var g=b.get,f=b.set;a._valueGet=function(){return B?g.call(this).split("").reverse().join(""):g.call(this)};a._valueSet=function(a){f.call(this,B?a.split("").reverse().join(""):a)};Object.defineProperty(a,"value",{get:function(){var a=d(this),b=d(this).data("_inputmask"),
c=b.masksets,f=b.activeMasksetIndex;return b&&b.opts.autoUnmask?a.inputmask("unmaskedvalue"):g.call(this)!=c[f]._buffer.join("")?g.call(this):""},set:function(a){f.call(this,a);d(this).triggerHandler("setvalue.inputmask")}})}}else document.__lookupGetter__&&a.__lookupGetter__("value")?a._valueGet||(g=a.__lookupGetter__("value"),f=a.__lookupSetter__("value"),a._valueGet=function(){return B?g.call(this).split("").reverse().join(""):g.call(this)},a._valueSet=function(a){f.call(this,B?a.split("").reverse().join(""):
a)},a.__defineGetter__("value",function(){var a=d(this),b=d(this).data("_inputmask"),c=b.masksets,f=b.activeMasksetIndex;return b&&b.opts.autoUnmask?a.inputmask("unmaskedvalue"):g.call(this)!=c[f]._buffer.join("")?g.call(this):""}),a.__defineSetter__("value",function(a){f.call(this,a);d(this).triggerHandler("setvalue.inputmask")})):(a._valueGet||(a._valueGet=function(){return B?this.value.split("").reverse().join(""):this.value},a._valueSet=function(a){this.value=B?a.split("").reverse().join(""):
a}),c(a.type))}function Z(a,c,b,d){var e=k();if(!1!==d)for(;!n(a)&&0<=a-1;)a--;for(d=a;d<c&&d<s();d++)if(n(d)){Q(e,d);var v=r(d),h=G(e,v);if(h!=N(v))if(v<s()&&!1!==t(d,h,!0)&&u()[w(d)].def==u()[w(v)].def)I(e,d,h,!0);else if(n(d))break}else Q(e,d);void 0!=b&&I(e,H(c),b);if(!1==f().greedy){c=S(e.join("")).split("");e.length=c.length;d=0;for(b=e.length;d<b;d++)e[d]=c[d];0==e.length&&(f().buffer=p().slice())}return a}function $(a,d,b){var e=k();if(G(e,a,!0)!=N(a))for(var h=H(d);h>a&&0<=h;h--)if(n(h)){var v=
H(h),m=G(e,v);m!=N(v)&&!1!==t(v,m,!0)&&u()[w(h)].def==u()[w(v)].def&&(I(e,h,m,!0),Q(e,v))}else Q(e,h);void 0!=b&&G(e,a)==N(a)&&I(e,a,b);a=e.length;if(!1==f().greedy){b=S(e.join("")).split("");e.length=b.length;h=0;for(v=e.length;h<v;h++)e[h]=b[h];0==e.length&&(f().buffer=p().slice())}return d-(a-e.length)}function aa(d,c,b){if(a.numericInput||B){switch(c){case a.keyCode.BACKSPACE:c=a.keyCode.DELETE;break;case a.keyCode.DELETE:c=a.keyCode.BACKSPACE}if(B){var g=b.end;b.end=b.begin;b.begin=g}}g=!0;b.begin==
b.end?(g=c==a.keyCode.BACKSPACE?b.begin-1:b.begin,a.isNumeric&&""!=a.radixPoint&&k()[g]==a.radixPoint&&(b.begin=k().length-1==g?b.begin:c==a.keyCode.BACKSPACE?g:r(g),b.end=b.begin),g=!1,c==a.keyCode.BACKSPACE?b.begin--:c==a.keyCode.DELETE&&b.end++):1!=b.end-b.begin||a.insertMode||(g=!1,c==a.keyCode.BACKSPACE&&b.begin--);E(k(),b.begin,b.end);var h=s();if(!1==a.greedy)Z(b.begin,h,void 0,!B&&c==a.keyCode.BACKSPACE&&!g);else{for(var v=b.begin,p=b.begin;p<b.end;p++)if(n(p)||!g)v=Z(b.begin,h,void 0,!B&&
c==a.keyCode.BACKSPACE&&!g);g||(b.begin=v)}c=r(-1);E(k(),b.begin,b.end,!0);J(d,!1,void 0==e[1]||c>=b.end,k());f().lastValidPosition<c?(f().lastValidPosition=-1,f().p=c):f().p=b.begin}function ba(e){V=!1;var c=this,b=d(c),g=e.keyCode,h=z(c);g==a.keyCode.BACKSPACE||g==a.keyCode.DELETE||fa&&127==g||e.ctrlKey&&88==g?(e.preventDefault(),88==g&&(M=k().join("")),aa(c,g,h),y(),C(c,k(),f().p),c._valueGet()==p().join("")&&b.trigger("cleared"),a.showTooltip&&b.prop("title",f().mask)):g==a.keyCode.END||g==a.keyCode.PAGE_DOWN?
setTimeout(function(){var b=r(f().lastValidPosition);a.insertMode||b!=s()||e.shiftKey||b--;z(c,e.shiftKey?h.begin:b,b)},0):g==a.keyCode.HOME&&!e.shiftKey||g==a.keyCode.PAGE_UP?z(c,0,e.shiftKey?h.begin:0):g==a.keyCode.ESCAPE||90==g&&e.ctrlKey?(J(c,!0,!1,M.split("")),b.click()):g!=a.keyCode.INSERT||e.shiftKey||e.ctrlKey?!1!=a.insertMode||e.shiftKey||(g==a.keyCode.RIGHT?setTimeout(function(){var a=z(c);z(c,a.begin)},0):g==a.keyCode.LEFT&&setTimeout(function(){var a=z(c);z(c,a.begin-1)},0)):(a.insertMode=
!a.insertMode,z(c,a.insertMode||h.begin!=s()?h.begin:h.begin-1));b=z(c);!0===a.onKeyDown.call(this,e,k(),a)&&z(c,b.begin,b.end);ca=-1!=d.inArray(g,a.ignorables)}function T(l,c,b,g,p,m){if(void 0==b&&V)return!1;V=!0;var q=d(this);l=l||window.event;b=c?b:l.which||l.charCode||l.keyCode;if(!(!0===c||l.ctrlKey&&l.altKey)&&(l.ctrlKey||l.metaKey||ca))return!0;if(b){!0!==c&&46==b&&!1==l.shiftKey&&","==a.radixPoint&&(b=44);var n,w,u=String.fromCharCode(b);c?(b=p?m:f().lastValidPosition+1,n={begin:b,end:b}):
n=z(this);m=B?1<n.begin-n.end||1==n.begin-n.end&&a.insertMode:1<n.end-n.begin||1==n.end-n.begin&&a.insertMode;var D=h;m&&(h=D,d.each(e,function(a,b){"object"==typeof b&&(h=a,f().undoBuffer=k().join(""))}),aa(this,a.keyCode.DELETE,n),a.insertMode||d.each(e,function(a,b){"object"==typeof b&&(h=a,$(n.begin,s()),f().lastValidPosition=r(f().lastValidPosition))}),h=D);var E=k().join("").indexOf(a.radixPoint);a.isNumeric&&!0!==c&&-1!=E&&(a.greedy&&n.begin<=E?(n.begin=H(n.begin),n.end=n.begin):u==a.radixPoint&&
(n.begin=E,n.end=n.begin));var F=n.begin;b=t(F,u,p);!0===p&&(b=[{activeMasksetIndex:h,result:b}]);var x=-1;d.each(b,function(b,d){h=d.activeMasksetIndex;f().writeOutBuffer=!0;var c=d.result;if(!1!==c){var e=!1,g=k();!0!==c&&(e=c.refresh,F=void 0!=c.pos?c.pos:F,u=void 0!=c.c?c.c:u);if(!0!==e){if(!0==a.insertMode){c=s();for(g=g.slice();G(g,c,!0)!=N(c)&&c>=F;)c=0==c?-1:H(c);c>=F?($(F,s(),u),g=f().lastValidPosition,c=r(g),c!=s()&&g>=F&&G(k().slice(),c,!0)!=N(c)&&(f().lastValidPosition=c)):f().writeOutBuffer=
!1}else I(g,F,u,!0);if(-1==x||x>r(F))x=r(F)}else!p&&(g=F<s()?F+1:F,-1==x||x>g)&&(x=g);x>f().p&&(f().p=x)}});!0!==p&&(h=D,y());if(!1!==g&&(d.each(b,function(a,b){if(b.activeMasksetIndex==h)return w=b,!1}),void 0!=w)){var J=this;setTimeout(function(){a.onKeyValidation.call(J,w.result,a)},0);if(f().writeOutBuffer&&!1!==w.result){var K=k();g=c?void 0:a.numericInput?F>E?H(x):u==a.radixPoint?x-1:H(x-1):x;C(this,K,g);!0!==c&&setTimeout(function(){!0===O(K)&&q.trigger("complete");P=!0;q.trigger("input")},
0)}else m&&(f().buffer=f().undoBuffer.split(""))}a.showTooltip&&q.prop("title",f().mask);l&&(l.preventDefault?l.preventDefault():l.returnValue=!1)}}function da(e){var c=d(this),b=e.keyCode,f=k();a.onKeyUp.call(this,e,f,a);b==a.keyCode.TAB&&a.showMaskOnFocus&&(c.hasClass("focus.inputmask")&&0==this._valueGet().length?(f=p().slice(),C(this,f),z(this,0),M=k().join("")):(C(this,f),f.join("")==p().join("")&&-1!=d.inArray(a.radixPoint,f)?(z(this,K(0)),c.click()):z(this,K(0),K(s()))))}function la(a){if(!0===
P)return P=!1,!0;a=d(this);J(this,!1,!1);C(this,k());!0===O(k())&&a.trigger("complete");a.click()}function ma(e){if(!0===P)return P=!1,!0;var c=d(this),b=z(this),f=this._valueGet();f.charAt(b.begin)==k()[b.begin]||f.charAt(b.begin+1)==k()[b.begin]||n(b.begin)?(J(this,!1,!1),C(this,k()),!0===O(k())&&c.trigger("complete"),c.click()):(e.keyCode=a.keyCode.BACKSPACE,ba.call(this,e));e.preventDefault()}function na(l){q=d(l);if(q.is(":input")){q.data("_inputmask",{masksets:e,activeMasksetIndex:h,opts:a,
isRTL:!1});a.showTooltip&&q.prop("title",f().mask);f().greedy=f().greedy?f().greedy:0==f().repeat;if(null!=q.attr("maxLength")){var c=q.prop("maxLength");-1<c&&d.each(e,function(a,b){"object"==typeof b&&"*"==b.repeat&&(b.repeat=c)});s()>=c&&-1<c&&(c<p().length&&(p().length=c),!1==f().greedy&&(f().repeat=Math.round(c/p().length)),q.prop("maxLength",2*s()))}ka(l);a.numericInput&&(a.isNumeric=a.numericInput);("rtl"==l.dir||a.numericInput&&a.rightAlignNumerics||a.isNumeric&&a.rightAlignNumerics)&&q.css("text-align",
"right");if("rtl"==l.dir||a.numericInput){l.dir="ltr";q.removeAttr("dir");var b=q.data("_inputmask");b.isRTL=!0;q.data("_inputmask",b);B=!0}q.unbind(".inputmask");q.removeClass("focus.inputmask");q.closest("form").bind("submit",function(){M!=k().join("")&&q.change()}).bind("reset",function(){setTimeout(function(){q.trigger("setvalue")},0)});q.bind("mouseenter.inputmask",function(){!d(this).hasClass("focus.inputmask")&&a.showMaskOnHover&&this._valueGet()!=k().join("")&&C(this,k())}).bind("blur.inputmask",
function(){var b=d(this),c=this._valueGet(),f=k();b.removeClass("focus.inputmask");M!=k().join("")&&b.change();a.clearMaskOnLostFocus&&""!=c&&(c==p().join("")?this._valueSet(""):U(this));!1===O(f)&&(b.trigger("incomplete"),a.clearIncomplete&&(d.each(e,function(a,b){"object"==typeof b&&(b.buffer=b._buffer.slice(),b.lastValidPosition=-1)}),h=0,a.clearMaskOnLostFocus?this._valueSet(""):(f=p().slice(),C(this,f))))}).bind("focus.inputmask",function(){var b=d(this),c=this._valueGet();a.showMaskOnFocus&&
!b.hasClass("focus.inputmask")&&(!a.showMaskOnHover||a.showMaskOnHover&&""==c)&&this._valueGet()!=k().join("")&&C(this,k(),r(f().lastValidPosition));b.addClass("focus.inputmask");M=k().join("")}).bind("mouseleave.inputmask",function(){var b=d(this);a.clearMaskOnLostFocus&&(b.hasClass("focus.inputmask")||this._valueGet()==b.attr("placeholder")||(this._valueGet()==p().join("")||""==this._valueGet()?this._valueSet(""):U(this)))}).bind("click.inputmask",function(){var b=this;setTimeout(function(){var c=
z(b),e=k();if(c.begin==c.end){var c=B?K(c.begin):c.begin,g=f().lastValidPosition,e=a.isNumeric?!1===a.skipRadixDance&&""!=a.radixPoint&&-1!=d.inArray(a.radixPoint,e)?a.numericInput?r(d.inArray(a.radixPoint,e)):d.inArray(a.radixPoint,e):r(g):r(g);c<e?n(c)?z(b,c):z(b,r(c)):z(b,e)}},0)}).bind("dblclick.inputmask",function(){var a=this;setTimeout(function(){z(a,0,r(f().lastValidPosition))},0)}).bind(ha+".inputmask dragdrop.inputmask drop.inputmask",function(b){if(!0===P)return P=!1,!0;var c=this,e=d(c);
if("propertychange"==b.type&&c._valueGet().length<=s())return!0;setTimeout(function(){var b=void 0!=a.onBeforePaste?a.onBeforePaste.call(this,c._valueGet()):c._valueGet();J(c,!0,!1,b.split(""),!0);!0===O(k())&&e.trigger("complete");e.click()},0)}).bind("setvalue.inputmask",function(){J(this,!0);M=k().join("");this._valueGet()==p().join("")&&this._valueSet("")}).bind("complete.inputmask",a.oncomplete).bind("incomplete.inputmask",a.onincomplete).bind("cleared.inputmask",a.oncleared).bind("keyup.inputmask",
da);q.bind("keydown.inputmask",ba).bind("keypress.inputmask",T).bind("keyup.inputmask",da);(W||X||Y)&&q.bind("input.inputmask",ma);ea&&q.bind("input.inputmask",la);J(l,!0,!1);M=k().join("");var g;try{g=document.activeElement}catch(m){}g===l?(q.addClass("focus.inputmask"),z(l,r(f().lastValidPosition))):a.clearMaskOnLostFocus?k().join("")==p().join("")?l._valueSet(""):U(l):C(l,k());ja(l)}}var B=!1,M=k().join(""),q,V=!1,P=!1,ca=!1;if(void 0!=m)switch(m.action){case "isComplete":return O(m.buffer);case "unmaskedvalue":return B=
m.$input.data("_inputmask").isRTL,ia(m.$input,m.skipDatepickerCheck);case "mask":na(m.el);break;case "format":return q=d({}),q.data("_inputmask",{masksets:e,activeMasksetIndex:h,opts:a,isRTL:a.numericInput}),a.numericInput&&(a.isNumeric=a.numericInput,B=!0),J(q,!1,!1,m.value.split(""),!0),k().join("");case "isValid":return q=d({}),q.data("_inputmask",{masksets:e,activeMasksetIndex:h,opts:a,isRTL:a.numericInput}),a.numericInput&&(a.isNumeric=a.numericInput,B=!0),J(q,!1,!0,m.value.split("")),O(k())}};
d.inputmask={defaults:{placeholder:"_",optionalmarker:{start:"[",end:"]"},quantifiermarker:{start:"{",end:"}"},groupmarker:{start:"(",end:")"},escapeChar:"\\",mask:null,oncomplete:d.noop,onincomplete:d.noop,oncleared:d.noop,repeat:0,greedy:!0,autoUnmask:!1,clearMaskOnLostFocus:!0,insertMode:!0,clearIncomplete:!1,aliases:{},onKeyUp:d.noop,onKeyDown:d.noop,onBeforePaste:void 0,onUnMask:void 0,showMaskOnFocus:!0,showMaskOnHover:!0,onKeyValidation:d.noop,skipOptionalPartCharacter:" ",showTooltip:!1,numericInput:!1,
isNumeric:!1,radixPoint:"",skipRadixDance:!1,rightAlignNumerics:!0,definitions:{9:{validator:"[0-9]",cardinality:1,definitionSymbol:"*"},a:{validator:"[A-Za-z\u0410-\u044f\u0401\u0451]",cardinality:1,definitionSymbol:"*"},"*":{validator:"[A-Za-z\u0410-\u044f\u0401\u04510-9]",cardinality:1}},keyCode:{ALT:18,BACKSPACE:8,CAPS_LOCK:20,COMMA:188,COMMAND:91,COMMAND_LEFT:91,COMMAND_RIGHT:93,CONTROL:17,DELETE:46,DOWN:40,END:35,ENTER:13,ESCAPE:27,HOME:36,INSERT:45,LEFT:37,MENU:93,NUMPAD_ADD:107,NUMPAD_DECIMAL:110,
NUMPAD_DIVIDE:111,NUMPAD_ENTER:108,NUMPAD_MULTIPLY:106,NUMPAD_SUBTRACT:109,PAGE_DOWN:34,PAGE_UP:33,PERIOD:190,RIGHT:39,SHIFT:16,SPACE:32,TAB:9,UP:38,WINDOWS:91},ignorables:[8,9,13,19,27,33,34,35,36,37,38,39,40,45,46,93,112,113,114,115,116,117,118,119,120,121,122,123],getMaskLength:function(d,h,a,m,f){f=d.length;h||("*"==a?f=m.length+1:1<a&&(f+=d.length*(a-1)));return f}},escapeRegex:function(d){return d.replace(RegExp("(\\/|\\.|\\*|\\+|\\?|\\||\\(|\\)|\\[|\\]|\\{|\\}|\\\\)","gim"),"\\$1")},format:function(e,
h){var a=d.extend(!0,{},d.inputmask.defaults,h);D(a.alias,h,a);return w(E(a),0,a,{action:"format",value:e})},isValid:function(e,h){var a=d.extend(!0,{},d.inputmask.defaults,h);D(a.alias,h,a);return w(E(a),0,a,{action:"isValid",value:e})}};d.fn.inputmask=function(e,h){var a=d.extend(!0,{},d.inputmask.defaults,h),m,f=0;if("string"===typeof e)switch(e){case "mask":return D(a.alias,h,a),m=E(a),0==m.length?this:this.each(function(){w(d.extend(!0,{},m),0,a,{action:"mask",el:this})});case "unmaskedvalue":var u=
d(this);return u.data("_inputmask")?(m=u.data("_inputmask").masksets,f=u.data("_inputmask").activeMasksetIndex,a=u.data("_inputmask").opts,w(m,f,a,{action:"unmaskedvalue",$input:u})):u.val();case "remove":return this.each(function(){var e=d(this);if(e.data("_inputmask")){m=e.data("_inputmask").masksets;f=e.data("_inputmask").activeMasksetIndex;a=e.data("_inputmask").opts;this._valueSet(w(m,f,a,{action:"unmaskedvalue",$input:e,skipDatepickerCheck:!0}));e.removeData("_inputmask");e.unbind(".inputmask");
e.removeClass("focus.inputmask");var h;Object.getOwnPropertyDescriptor&&(h=Object.getOwnPropertyDescriptor(this,"value"));h&&h.get?this._valueGet&&Object.defineProperty(this,"value",{get:this._valueGet,set:this._valueSet}):document.__lookupGetter__&&this.__lookupGetter__("value")&&this._valueGet&&(this.__defineGetter__("value",this._valueGet),this.__defineSetter__("value",this._valueSet));try{delete this._valueGet,delete this._valueSet}catch(t){this._valueSet=this._valueGet=void 0}}});case "getemptymask":return this.data("_inputmask")?
(m=this.data("_inputmask").masksets,f=this.data("_inputmask").activeMasksetIndex,m[f]._buffer.join("")):"";case "hasMaskedValue":return this.data("_inputmask")?!this.data("_inputmask").opts.autoUnmask:!1;case "isComplete":return m=this.data("_inputmask").masksets,f=this.data("_inputmask").activeMasksetIndex,a=this.data("_inputmask").opts,w(m,f,a,{action:"isComplete",buffer:this[0]._valueGet().split("")});case "getmetadata":if(this.data("_inputmask"))return m=this.data("_inputmask").masksets,f=this.data("_inputmask").activeMasksetIndex,
m[f].metadata;break;default:return D(e,h,a)||(a.mask=e),m=E(a),0==m.length?this:this.each(function(){w(d.extend(!0,{},m),f,a,{action:"mask",el:this})})}else{if("object"==typeof e)return a=d.extend(!0,{},d.inputmask.defaults,e),D(a.alias,e,a),m=E(a),0==m.length?this:this.each(function(){w(d.extend(!0,{},m),f,a,{action:"mask",el:this})});if(void 0==e)return this.each(function(){var e=d(this).attr("data-inputmask");if(e&&""!=e)try{var e=e.replace(RegExp("'","g"),'"'),f=d.parseJSON("{"+e+"}");d.extend(!0,
f,h);a=d.extend(!0,{},d.inputmask.defaults,f);D(a.alias,f,a);a.alias=void 0;d(this).inputmask(a)}catch(m){}})}}}})(jQuery);
