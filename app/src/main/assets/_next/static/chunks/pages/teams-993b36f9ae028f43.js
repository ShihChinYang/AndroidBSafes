(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[2219],{38206:function(e,a,r){(window.__NEXT_P=window.__NEXT_P||[]).push(["/teams",function(){return r(39808)}])},42017:function(e,a,r){"use strict";r.d(a,{Z:function(){return g}});var s=r(85893),t=r(68888),n=r(88083),l=r(94184),i=r.n(l),c=r(67294),d=r(54728),o=r(13551);let u=c.forwardRef((e,a)=>{let{active:r=!1,disabled:t=!1,className:n,style:l,activeLabel:c="(current)",children:d,linkStyle:u,linkClassName:f,as:m=o.Z,...x}=e,h=r||t?"span":m;return(0,s.jsx)("li",{ref:a,style:l,className:i()(n,"page-item",{active:r,disabled:t}),children:(0,s.jsxs)(h,{className:i()("page-link",f),style:u,...x,children:[d,r&&c&&(0,s.jsx)("span",{className:"visually-hidden",children:c})]})})});function f(e,a){let r=arguments.length>2&&void 0!==arguments[2]?arguments[2]:e,t=c.forwardRef((e,t)=>{let{children:n,...l}=e;return(0,s.jsxs)(u,{...l,ref:t,children:[(0,s.jsx)("span",{"aria-hidden":"true",children:n||a}),(0,s.jsx)("span",{className:"visually-hidden",children:r})]})});return t.displayName=e,t}u.displayName="PageItem";let m=f("First","\xab"),x=f("Prev","‹","Previous"),h=f("Ellipsis","…","More"),j=f("Next","›"),p=f("Last","\xbb"),v=c.forwardRef((e,a)=>{let{bsPrefix:r,className:t,size:n,...l}=e,c=(0,d.vE)(r,"pagination");return(0,s.jsx)("ul",{ref:a,...l,className:i()(t,c,n&&"".concat(c,"-").concat(n))})});v.displayName="Pagination";var N=Object.assign(v,{First:m,Prev:x,Ellipsis:h,Item:u,Next:j,Last:p});function g(e){let{page:a,total:r,limit:l,changePage:i}=e,c=[],d=Math.ceil(r/l),o=Math.ceil(d/5);for(let e=1;e<=o;e++){let r=!1;5*e>=d&&(r=!0);let t=[];for(let n=(e-1)*5+1;n<=(r?d:5*e);n++)t.push((0,s.jsx)(N.Item,{active:n===a,onClick:()=>i(n),children:n},n));c.push((0,s.jsx)(n.Z,{children:(0,s.jsx)(N,{children:t})},e))}return(0,s.jsx)(t.Z,{children:c})}},49614:function(e,a,r){"use strict";var s=r(94184),t=r.n(s),n=r(67294),l=r(54728),i=r(85893);let c=n.forwardRef((e,a)=>{let{bsPrefix:r,size:s,vertical:n=!1,className:c,role:d="group",as:o="div",...u}=e,f=(0,l.vE)(r,"btn-group"),m=f;return n&&(m="".concat(f,"-vertical")),(0,i.jsx)(o,{...u,ref:a,role:d,className:t()(c,m,s&&"".concat(f,"-").concat(s))})});c.displayName="ButtonGroup",a.Z=c},5401:function(e,a,r){"use strict";r.d(a,{Z:function(){return Z}});var s=r(94184),t=r.n(s),n=r(67294),l=r(54728),i=r(85893);let c=n.forwardRef((e,a)=>{let{className:r,bsPrefix:s,as:n="div",...c}=e;return s=(0,l.vE)(s,"card-body"),(0,i.jsx)(n,{ref:a,className:t()(r,s),...c})});c.displayName="CardBody";let d=n.forwardRef((e,a)=>{let{className:r,bsPrefix:s,as:n="div",...c}=e;return s=(0,l.vE)(s,"card-footer"),(0,i.jsx)(n,{ref:a,className:t()(r,s),...c})});d.displayName="CardFooter";var o=r(54921);let u=n.forwardRef((e,a)=>{let{bsPrefix:r,className:s,as:c="div",...d}=e,u=(0,l.vE)(r,"card-header"),f=(0,n.useMemo)(()=>({cardHeaderBsPrefix:u}),[u]);return(0,i.jsx)(o.Z.Provider,{value:f,children:(0,i.jsx)(c,{ref:a,...d,className:t()(s,u)})})});u.displayName="CardHeader";let f=n.forwardRef((e,a)=>{let{bsPrefix:r,className:s,variant:n,as:c="img",...d}=e,o=(0,l.vE)(r,"card-img");return(0,i.jsx)(c,{ref:a,className:t()(n?"".concat(o,"-").concat(n):o,s),...d})});f.displayName="CardImg";let m=n.forwardRef((e,a)=>{let{className:r,bsPrefix:s,as:n="div",...c}=e;return s=(0,l.vE)(s,"card-img-overlay"),(0,i.jsx)(n,{ref:a,className:t()(r,s),...c})});m.displayName="CardImgOverlay";let x=n.forwardRef((e,a)=>{let{className:r,bsPrefix:s,as:n="a",...c}=e;return s=(0,l.vE)(s,"card-link"),(0,i.jsx)(n,{ref:a,className:t()(r,s),...c})});x.displayName="CardLink";var h=r(68236);let j=(0,h.Z)("h6"),p=n.forwardRef((e,a)=>{let{className:r,bsPrefix:s,as:n=j,...c}=e;return s=(0,l.vE)(s,"card-subtitle"),(0,i.jsx)(n,{ref:a,className:t()(r,s),...c})});p.displayName="CardSubtitle";let v=n.forwardRef((e,a)=>{let{className:r,bsPrefix:s,as:n="p",...c}=e;return s=(0,l.vE)(s,"card-text"),(0,i.jsx)(n,{ref:a,className:t()(r,s),...c})});v.displayName="CardText";let N=(0,h.Z)("h5"),g=n.forwardRef((e,a)=>{let{className:r,bsPrefix:s,as:n=N,...c}=e;return s=(0,l.vE)(s,"card-title"),(0,i.jsx)(n,{ref:a,className:t()(r,s),...c})});g.displayName="CardTitle";let y=n.forwardRef((e,a)=>{let{bsPrefix:r,className:s,bg:n,text:d,border:o,body:u=!1,children:f,as:m="div",...x}=e,h=(0,l.vE)(r,"card");return(0,i.jsx)(m,{ref:a,...x,className:t()(s,h,n&&"bg-".concat(n),d&&"text-".concat(d),o&&"border-".concat(o)),children:u?(0,i.jsx)(c,{children:f}):f})});y.displayName="Card";var Z=Object.assign(y,{Img:f,Title:g,Subtitle:p,Body:c,Link:x,Text:v,Header:u,Footer:d,ImgOverlay:m})},39808:function(e,a,r){"use strict";r.r(a),r.d(a,{default:function(){return b}});var s=r(85893),t=r(67294),n=r(9473),l=r(41664),i=r.n(l),c=r(33353),d=r(68888),o=r(88083),u=r(5401),f=r(81011),m=r(76529),x=r(56693),h=r(14153),j=r(13425);function p(e){let{show:a=!1,handleClose:r,handleCreateANewTeam:n}=e;(0,j.qu)(!1,"Rendering NewTeamModal: ","".concat(a,"}"));let l=(0,t.useRef)(null);return(0,s.jsx)(x.Z,{show:a,onHide:r,onEntered:()=>{l.current.focus()},children:(0,s.jsxs)(x.Z.Body,{children:[(0,s.jsx)("h3",{children:"Title"}),(0,s.jsx)(h.Z.Control,{ref:l,size:"lg",type:"text"}),(0,s.jsx)("br",{}),(0,s.jsx)("br",{}),(0,s.jsx)(m.Z,{variant:"primary",onClick:()=>{n(l.current.value)},className:"pull-right",children:"Create"}),(0,s.jsx)(m.Z,{variant:"secondary",onClick:r,className:"pull-right",children:"Close"})]})})}var v=r(82354),N=r.n(v);function g(e){let{addATeam:a}=e;return(0,s.jsx)(s.Fragment,{children:(0,s.jsx)(m.Z,{variant:"primary",className:N().btnCircle,onClick:()=>a("addATeamOnTop"),children:(0,s.jsx)("i",{id:"1",className:"fa fa-plus fa-lg","aria-hidden":"true"})})})}var y=r(49614),Z=r(53918);function w(e){let{index:a,team:r,onAdd:n}=e,l=r.id,c=document.createElement("span");c.innerHTML=r.title;let f=c.textContent||c.innerText,m=(0,t.forwardRef)(function(e,a){let{children:r,onClick:t}=e;return(0,s.jsxs)("a",{href:"",ref:a,onClick:e=>{e.preventDefault(),t(e)},children:[(0,s.jsx)("i",{className:"fa fa-plus text-dark","aria-hidden":"true"}),r]})}),x=e=>{n({action:e,index:a,targetTeam:l,targetPosition:r.position})};return(0,s.jsx)(s.Fragment,{children:(0,s.jsx)(u.Z,{className:"",style:{cursor:"pointer"},children:(0,s.jsx)(u.Z.Body,{className:"",children:(0,s.jsxs)(d.Z,{className:"",children:[(0,s.jsx)(i(),{href:"/team/".concat(r.id),legacyBehavior:!0,children:(0,s.jsx)(o.Z,{xs:9,children:(0,s.jsx)("div",{children:(0,s.jsx)("h2",{dangerouslySetInnerHTML:{__html:f}})})})}),(0,s.jsx)(o.Z,{xs:3,children:(0,s.jsx)(y.Z,{className:"pull-right",children:(0,s.jsxs)(Z.Z,{align:"end",className:"justify-content-end",children:[(0,s.jsx)(Z.Z.Toggle,{as:m,variant:"link"}),(0,s.jsxs)(Z.Z.Menu,{children:[(0,s.jsx)(Z.Z.Item,{onClick:()=>x("addATeamBefore"),children:"Add before"}),(0,s.jsx)(Z.Z.Item,{onClick:()=>x("addATeamAfter"),children:"Add after"})]})]})})})]})})})})}var C=r(42017),E=r(40699),T=r(76800);function b(){let e=(0,n.I0)(),[a,r]=(0,t.useState)(!1),l=(0,n.v9)(e=>e.auth.isLoggedIn),m=(0,n.v9)(e=>e.auth.publicKey),x=(0,n.v9)(e=>e.team.teams),h=(0,n.v9)(e=>e.team.pageNumber),v=(0,n.v9)(e=>e.team.itemsPerPage),y=(0,n.v9)(e=>e.team.total),[Z,b]=(0,t.useState)(null),[k,R]=(0,t.useState)(null),[_,P]=(0,t.useState)(null),[I,A]=(0,t.useState)(null),[B,O]=(0,t.useState)(!1),S=e=>{let{action:a="addATeamOnTop",index:r,targetTeam:s,targetPosition:t}=e;b(a),R(r),P(s),A(t),O(!0)},M=async a=>{(0,j.qu)(!1,"createANewTeam",a),O(!1),e((0,T.lR)({title:a,addAction:Z,targetIndex:k,targetTeam:_,targetPosition:I,publicKeyPem:m}))},L=function(){let a=arguments.length>0&&void 0!==arguments[0]?arguments[0]:1;e((0,T.BA)({pageNumber:a})),r(!1)};return(0,t.useEffect)(()=>{(0,j.qu)(!1,"loggedIn value: ",l),l&&!a&&(e((0,E.k_)()),r(!0))},[l]),(0,t.useEffect)(()=>{a&&L()},[a]),(0,s.jsx)("div",{className:N().teamsBackground,children:(0,s.jsx)(f.Z,{children:(0,s.jsxs)(c.Z,{fluid:!0,children:[(0,s.jsx)("br",{}),(0,s.jsx)(d.Z,{children:(0,s.jsxs)(o.Z,{sm:{span:10,offset:1},md:{span:8,offset:2},children:[(0,s.jsx)(u.Z,{children:(0,s.jsx)(i(),{href:"/safe",legacyBehavior:!0,children:(0,s.jsxs)(u.Z.Body,{children:[(0,s.jsx)("i",{className:"fa fa-heart text-danger"}),(0,s.jsx)("h2",{children:"Personal"})]})})}),(0,s.jsx)("br",{}),(0,s.jsx)("p",{hidden:!0,className:"text-muted",children:"Only you can access items in your personal workspace."})]})}),(0,s.jsx)(d.Z,{className:"justify-content-center",children:(0,s.jsxs)(o.Z,{lg:8,children:[(0,s.jsx)(d.Z,{className:"justify-content-center",children:(0,s.jsx)(g,{addATeam:S})}),(0,s.jsx)(p,{show:B,handleClose:()=>O(!1),handleCreateANewTeam:M})]})}),(0,s.jsx)("br",{}),(0,s.jsx)("br",{}),(0,s.jsx)(d.Z,{children:(0,s.jsx)(o.Z,{sm:{span:10,offset:1},md:{span:8,offset:2},children:x.map((e,a)=>(0,s.jsx)(w,{index:a,team:e,onAdd:S},a))})}),x&&x.length>0&&(0,s.jsx)(d.Z,{children:(0,s.jsx)(o.Z,{sm:{span:10,offset:1},md:{span:8,offset:2},children:(0,s.jsx)("div",{className:"mt-4 d-flex justify-content-center",children:(0,s.jsx)(C.Z,{page:h,total:y,limit:v,changePage:e=>{L(e)},ellipsis:1})})})})]})})})}}},function(e){e.O(0,[3937,1664,1011,2888,9774,179],function(){return e(e.s=38206)}),_N_E=e.O()}]);