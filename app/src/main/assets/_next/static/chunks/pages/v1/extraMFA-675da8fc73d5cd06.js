(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[2738],{98009:function(e,t,n){(window.__NEXT_P=window.__NEXT_P||[]).push(["/v1/extraMFA",function(){return n(3655)}])},18001:function(e,t,n){"use strict";n.d(t,{Z:function(){return o}});var a=n(85893),s=n(67294),r=n(76529),c=n(14153),i=n(51417),l=n(82354),u=n.n(l);function o(e){let{onKeyChanged:t,recoveredKeyPassword:n,focus:l=!1}=e,o=(0,s.useRef)(null),[f,d]=(0,s.useState)(""),[h,x]=(0,s.useState)(!0);return(0,s.useEffect)(()=>{n&&d(n)},[n]),(0,s.useEffect)(()=>{l&&o.current.focus()},[l]),(0,a.jsx)(a.Fragment,{children:(0,a.jsxs)(i.Z,{children:[(0,a.jsx)(c.Z.Control,{ref:o,type:h?"password":"text",onChange:e=>{d(e.target.value),t(e.target.value)},value:f,className:u().inputBox}),(0,a.jsx)(r.Z,{className:u().inputButton,onClick:e=>{x(!h)},variant:"dark",children:h?(0,a.jsx)("i",{id:"1",className:"fa fa-eye-slash fa-lg","aria-hidden":"true"}):(0,a.jsx)("i",{id:"1",className:"fa fa-eye fa-lg","aria-hidden":"true"})})]})})}n(13425)},52448:function(e,t,n){"use strict";var a=n(94184),s=n.n(a),r=n(67294),c=n(54728),i=n(85893);let l=r.forwardRef((e,t)=>{let{bsPrefix:n,variant:a,animation:r="border",size:l,as:u="div",className:o,...f}=e;n=(0,c.vE)(n,"spinner");let d="".concat(n,"-").concat(r);return(0,i.jsx)(u,{ref:t,...f,className:s()(o,d,l&&"".concat(d,"-").concat(l),a&&"text-".concat(a))})});l.displayName="Spinner",t.Z=l},3655:function(e,t,n){"use strict";n.r(t),n.d(t,{default:function(){return m}});var a=n(85893),s=n(67294),r=n(9473),c=n(11163),i=n(33353),l=n(68888),u=n(88083),o=n(14153),f=n(76529),d=n(52448),h=n(82354),x=n.n(h);n(13425);var p=n(81011);n(18001);var v=n(3706);function m(){let e=(0,r.I0)(),t=(0,c.useRouter)(),[n,h]=(0,s.useState)(""),m=(0,r.v9)(e=>e.v1Account.activity),j=(0,r.v9)(e=>e.v1Account.nextAuthStep);return(0,s.useEffect)(()=>{j&&"KeyRequired"===j.step&&t.push("/v1/keyEnter")},[j]),(0,a.jsx)("div",{className:x().managedMemberLoginBackground,children:(0,a.jsx)(p.Z,{showNavbarMenu:!1,showPathRow:!1,children:(0,a.jsxs)(i.Z,{className:"mt-5",children:[(0,a.jsx)(l.Z,{className:"text-center",children:(0,a.jsx)("h1",{className:"display-5",children:"Multi-Factor Authentication"})}),(0,a.jsx)("br",{}),(0,a.jsx)(l.Z,{children:(0,a.jsxs)(u.Z,{xs:{span:6,offset:3},sm:{span:6,offset:3},md:{span:6,offset:3},lg:{span:6,offset:3},children:[(0,a.jsxs)(o.Z.Group,{className:"mb-3",controlId:"MFA",children:[(0,a.jsx)(o.Z.Label,{children:"Please enter the token"}),(0,a.jsx)(o.Z.Control,{className:"py-2",type:"text",value:n,onChange:e=>{h(e.target.value)},placeholder:"",autoComplete:"off"})]}),(0,a.jsx)(f.Z,{variant:"primary",className:"py-2",onClick:t=>{e((0,v.Sf)({MFAToken:n}))},children:"VerifyMFA"===m?(0,a.jsx)(d.Z,{animation:"border"}):"Verify"}),"InvalidMFA"===m&&(0,a.jsx)("p",{className:"text-danger",children:"Invalid Token"})]})})]})})})}}},function(e){e.O(0,[3937,1011,2888,9774,179],function(){return e(e.s=98009)}),_N_E=e.O()}]);