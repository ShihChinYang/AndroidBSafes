(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[4049],{27724:function(e,n,s){(window.__NEXT_P=window.__NEXT_P||[]).push(["/n/[accountNickname]",function(){return s(60493)}])},52448:function(e,n,s){"use strict";var a=s(94184),c=s.n(a),t=s(67294),r=s(54728),o=s(85893);let l=t.forwardRef((e,n)=>{let{bsPrefix:s,variant:a,animation:t="border",size:l,as:i="div",className:d,...u}=e;s=(0,r.vE)(s,"spinner");let m="".concat(s,"-").concat(t);return(0,o.jsx)(i,{ref:n,...u,className:c()(d,m,l&&"".concat(m,"-").concat(l),a&&"text-".concat(a))})});l.displayName="Spinner",n.Z=l},60493:function(e,n,s){"use strict";s.r(n),s.d(n,{default:function(){return N}});var a=s(85893),c=s(67294),t=s(9473),r=s(11163),o=s(33353),l=s(68888),i=s(88083),d=s(14153),u=s(76529),m=s(52448),x=s(82354),h=s.n(x),f=s(81011),j=s(13425),p=s(3706);function N(){let e=(0,t.I0)(),n=(0,r.useRouter)(),[s,x]=(0,c.useState)(""),[N,b]=(0,c.useState)(""),Z=(0,t.v9)(e=>e.v1Account.activity),v=(0,t.v9)(e=>e.v1Account.masterId),g=(0,t.v9)(e=>e.v1Account.displayMasterId);return(0,t.v9)(e=>e.v1Account.nextAuthStep),(0,c.useEffect)(()=>{if(n.query.accountNickname){let s=n.query.accountNickname;e((0,p.VI)({nickname:s}))}},[n.query.accountNickname]),(0,a.jsx)("div",{className:h().managedMemberLoginBackground,children:(0,a.jsx)(f.Z,{showNavbarMenu:!1,showPathRow:!1,children:(0,a.jsxs)(o.Z,{className:"mt-5 ",children:[(0,a.jsx)(l.Z,{children:(0,a.jsx)(i.Z,{children:(0,a.jsx)("h1",{className:"display-5 text-center",children:"Member Sign In"})})}),(0,a.jsx)("br",{}),(0,a.jsx)(l.Z,{children:(0,a.jsx)(i.Z,{xs:{span:12,offset:0},sm:{span:12,offset:0},md:{span:10,offset:1},lg:{span:8,offset:2},children:(0,a.jsxs)(d.Z,{style:{backgroundColor:"white"},children:[(0,a.jsx)("br",{}),(0,a.jsxs)(i.Z,{xs:{span:10,offset:1},sm:{span:8,offset:2},children:[(0,a.jsxs)(d.Z.Group,{className:"mb-3",controlId:"exampleForm.ControlInput1",children:[(0,a.jsx)(d.Z.Label,{children:"Account ID"}),"NicknameSignIn"===Z&&(0,a.jsx)(l.Z,{className:"justify-content-center",children:(0,a.jsx)(m.Z,{animation:"border"})}),"NicknameSignInError"===Z&&(0,a.jsx)("p",{className:"text-danger",children:"Invalid Account !"}),(0,a.jsx)("h4",{children:g})]}),(0,a.jsx)("br",{}),(0,a.jsxs)(d.Z.Group,{className:"mb-3",controlId:"MemberName",children:[(0,a.jsx)(d.Z.Label,{children:"Member Name"}),(0,a.jsx)(d.Z.Control,{className:"py-2",value:s,onChange:e=>{x(e.target.value)},type:"text",placeholder:"",autoComplete:"off"})]}),(0,a.jsxs)(d.Z.Group,{className:"mb-3",controlId:"keyPassword",children:[(0,a.jsx)(d.Z.Label,{children:"Password"}),(0,a.jsx)(d.Z.Control,{className:"py-2",value:N,onChange:e=>{b(e.target.value)},type:"password",placeholder:"",autoComplete:"off"})]}),(0,a.jsx)(l.Z,{children:(0,a.jsx)(i.Z,{children:(0,a.jsx)(u.Z,{className:"pull-right",onClick:n=>{(0,j.qu)(!1,"".concat(s," ").concat(N)),e((0,p.CH)({masterId:v,memberName:s,password:N}))},children:"AuthenticateManagedMember"===Z?(0,a.jsx)(m.Z,{animation:"border"}):"Done"})})}),"InvalidMember"===Z&&(0,a.jsx)("p",{className:"text-danger",children:"Invalid member !"})]}),(0,a.jsx)("br",{})]})})})]})})})}}},function(e){e.O(0,[3937,1011,2888,9774,179],function(){return e(e.s=27724)}),_N_E=e.O()}]);