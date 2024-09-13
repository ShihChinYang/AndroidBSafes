"use strict";(self.webpackChunk_N_E=self.webpackChunk_N_E||[]).push([[318],{60389:function(e,r,t){t.d(r,{Z:function(){return d}});var n=t(85893),a=t(67294),s=t(76529),l=t(87383),i=t(82354),c=t.n(i),o=t(13425);function d(e){let{forcedType:r=null,addAnItem:t,pageOnly:i=!1}=e,[d,u]=(0,a.useState)(!1);return(0,n.jsxs)(n.Fragment,{children:[(0,n.jsx)(s.Z,{variant:"primary",className:c().btnCircle,onClick:()=>{r?t(r,"addAnItemOnTop"):u(!0)},children:(0,n.jsx)("i",{id:"1",className:"fa fa-plus fa-lg","aria-hidden":"true"})}),(0,n.jsx)(l.Z,{pageOnly:i,show:d,handleClose:()=>u(!1),optionSelected:e=>{(0,o.qu)(!1,e),u(!1),t(e,"addAnItemOnTop")}})]})}},63604:function(e,r,t){t.d(r,{Z:function(){return o}});var n=t(85893),a=t(67294),s=t(56693),l=t(76529),i=t(14153),c=t(13425);function o(e){let{show:r=!1,handleClose:t,handleCreateANewItem:o}=e;(0,c.qu)(!1,"Rendering NewItemModal: ","".concat(r,"}"));let d=(0,a.useRef)(null);return(0,n.jsx)(s.Z,{show:r,onHide:t,onEntered:()=>{d.current.focus()},children:(0,n.jsxs)(s.Z.Body,{children:[(0,n.jsx)("h3",{children:"Title"}),(0,n.jsx)(i.Z.Control,{ref:d,size:"lg",type:"text"}),(0,n.jsx)("br",{}),(0,n.jsx)("br",{}),(0,n.jsx)(l.Z,{variant:"primary",onClick:()=>{o(d.current.value)},className:"pull-right",children:"Create"}),(0,n.jsx)(l.Z,{variant:"secondary",onClick:t,className:"pull-right",children:"Close"})]})})}},92442:function(e,r,t){t.d(r,{Z:function(){return I}});var n=t(85893),a=t(67294),s=t(9473),l=t(11163),i=t(41664),c=t.n(i),o=t(33353),d=t(68888),u=t(88083),h=t(76529),x=t(14153),f=t(51417),j=t(5401),m=t(82354),p=t.n(m),g=t(60389),v=t(63604),Z=t(31719),b=t(42017),y=t(92867),N=t(40699),C=t(84390),w=t(13425);let k=-1!=="default;".indexOf("hide"),E=-1!=="default;".indexOf("pageOnly");function I(e){let{readyToList:r=!1}=e;(0,w.qu)(!1,"Rendering Workspace");let t=(0,l.useRouter)(),i=(0,s.I0)(),m=(0,s.v9)(e=>e.container.workspace),I=(0,s.v9)(e=>e.container.workspaceKey),R=(0,s.v9)(e=>e.container.workspaceKeyReady),S=(0,s.v9)(e=>e.container.searchKey),A=(0,s.v9)(e=>e.container.searchIV),D=(0,s.v9)(e=>e.container.container),T=(0,s.v9)(e=>e.container.mode),B=(0,s.v9)(e=>e.container.listingDone),[O,q]=(0,a.useState)(""),H=(0,s.v9)(e=>e.container.items),P=(0,s.v9)(e=>e.container.newItem),_=(0,s.v9)(e=>e.container.pageNumber),F=(0,s.v9)(e=>e.container.itemsPerPage),K=(0,s.v9)(e=>e.container.total),[z,G]=(0,a.useState)(null),[L,W]=(0,a.useState)(null),[V,J]=(0,a.useState)(null),[M,Y]=(0,a.useState)(null),[Q,U]=(0,a.useState)(!1),X=(e,r,t,n)=>{(0,w.qu)(!1,"".concat(e," ").concat(r," ").concat(t," ").concat(n)),ee(e,r,t,n)},$=H.map((e,r)=>(0,n.jsx)(Z.Z,{itemIndex:r,item:e,onAdd:X},r)),ee=function(e,r){let t=arguments.length>2&&void 0!==arguments[2]?arguments[2]:null,n=arguments.length>3&&void 0!==arguments[3]?arguments[3]:null;G(e),W(r),J(t),Y(n),U(!0)},er=async e=>{(0,w.qu)(!1,"createANewItem",e),U(!1),i((0,N.Ag)({titleStr:e,currentContainer:m,selectedItemType:z,addAction:L,targetItem:V,targetPosition:M,workspaceKey:I,searchKey:S,searchIV:A}))},et=e=>{e.preventDefault(),i((0,N.zs)({searchValue:O,pageNumber:1}))},en=e=>{let{pageNumber:r=1,searchMode:t}=e,n=t||T;"listAll"===n?i((0,N.bZ)({pageNumber:r})):"search"===n&&i((0,N.zs)({searchValue:O,pageNumber:r}))};return(0,a.useEffect)(()=>{let e=(e,r)=>{let{shallow:t}=r;console.log("App is changing to ".concat(e," ").concat(t?"with":"without"," shallow routing")),i((0,N.No)(!1)),i((0,C.JG)()),i((0,C.rs)()),i((0,N.H5)())},r=()=>{(0,w.qu)(!1,"handleRouteChangeComplete")};return t.events.on("routeChangeStart",e),t.events.on("routeChangeComplete",r),()=>{t.events.off("routeChangeStart",e),t.events.off("routeChangeComplete",r)}},[]),(0,a.useEffect)(()=>{if((0,w.qu)(!1,"workspaceKeyReady: ".concat(R," ")),r&&m&&R&&"root"===D&&((0,w.qu)(!1,"listItemsThunk"),i((0,C.df)()),i((0,C.rs)()),!k)){let e=[{_id:m}];i((0,C.rJ)(e)),i((0,N.bZ)({pageNumber:1}))}},[r,D,m,R]),(0,a.useEffect)(()=>{if(P){let e=(0,y.vp)(P,!0);i((0,N.Ej)()),t.push(e)}},[P]),(0,n.jsxs)(o.Z,{className:p().container,children:[k&&(0,n.jsxs)(n.Fragment,{children:[(0,n.jsx)("br",{}),(0,n.jsx)("br",{}),(0,n.jsx)("br",{})]}),(0,n.jsx)(d.Z,{hidden:k,children:(0,n.jsx)(x.Z,{onSubmit:et,children:(0,n.jsxs)(f.Z,{className:"mb-3",children:[(0,n.jsx)(x.Z.Control,{size:"lg",type:"text",value:O,onChange:e=>{(0,w.qu)(!1,"search value:",e.target.value),q(e.target.value)}}),(0,n.jsx)(h.Z,{variant:"link",children:(0,n.jsx)("i",{id:"1",className:"fa fa-search fa-lg text-dark","aria-hidden":"true",onClick:et})})]})})}),(0,n.jsx)(d.Z,{className:"justify-content-center",children:E?(0,n.jsx)(g.Z,{forcedType:"Page",addAnItem:ee}):(0,n.jsx)(g.Z,{addAnItem:ee})}),(0,n.jsx)(v.Z,{show:Q,handleClose:()=>U(!1),handleCreateANewItem:er}),(0,n.jsx)("br",{}),(0,n.jsx)("br",{}),"search"===T&&(0,n.jsxs)(n.Fragment,{children:[(0,n.jsx)(d.Z,{children:(0,n.jsx)(u.Z,{children:(0,n.jsx)(h.Z,{variant:"default",className:"".concat(p().btnCircle," pull-right"),onClick:e=>{e.preventDefault(),q(""),i((0,N.bZ)({pageNumber:1}))},children:(0,n.jsx)("i",{id:"1",className:"fa fa-times fa-lg","aria-hidden":"true"})})})}),(0,n.jsx)("br",{})]}),k&&(0,n.jsx)(d.Z,{className:"justify-content-center",children:(0,n.jsx)(u.Z,{sm:8,children:(0,n.jsxs)(j.Z,{children:[(0,n.jsx)(j.Z.Header,{children:"\uD83D\uDD30 Welcome!"}),(0,n.jsxs)(j.Z.Body,{children:[(0,n.jsx)(j.Z.Title,{children:"Instructions"}),(0,n.jsxs)("ul",{children:[(0,n.jsxs)("li",{children:[(0,n.jsx)("strong",{children:"Hide"})," - Click the blue + button to hide a page full of words, videos, photos, and files."]}),(0,n.jsxs)("li",{children:[(0,n.jsx)("strong",{children:"View"})," - You can access your hidden pages on any device with a browser. Go to bsafes.com and open your ",(0,n.jsx)("span",{style:{backgroundColor:"#990000",color:"white",fontWeight:"bold",padding:"7px"},children:"BSafes"}),"."]})]})]})]})})}),B&&"search"!==T&&0===$.length&&(0,n.jsx)(d.Z,{className:"justify-content-center",children:(0,n.jsx)(u.Z,{sm:8,children:(0,n.jsxs)(j.Z,{children:[(0,n.jsx)(j.Z.Header,{children:"\uD83D\uDD30 Welcome!"}),(0,n.jsxs)(j.Z.Body,{children:[(0,n.jsx)(j.Z.Title,{children:"Instructions"}),(0,n.jsxs)("ul",{children:[(0,n.jsxs)("li",{children:[(0,n.jsx)("strong",{children:"Get Started"})," - Add a page item, then write and add photos, videos, and files."]}),(0,n.jsxs)("li",{children:[(0,n.jsx)("strong",{children:"Adding an items"})," - Click on the central blue button, then select an item type."]}),(0,n.jsxs)("li",{children:[(0,n.jsx)("strong",{children:"Searching for items"})," - Enter keywords in central field, then click on search button."]}),(0,n.jsxs)("li",{children:[(0,n.jsx)("strong",{children:"Opening a new tab"})," - Click on the blue button on upper right, then select an item."]}),(0,n.jsxs)("li",{children:[(0,n.jsx)("strong",{children:"Lock"})," - Click on the Lock button on upper right."]}),(0,n.jsx)("li",{children:(0,n.jsx)("a",{href:"https://support.bsafes.com",target:"_blank",rel:"noopener noreferrer",style:{},children:(0,n.jsx)("strong",{children:"Support"})})})]})]})]})})}),$,H&&H.length>0&&(0,n.jsx)(d.Z,{children:(0,n.jsx)(u.Z,{sm:{span:10,offset:1},md:{span:8,offset:2},children:(0,n.jsx)("div",{className:"mt-4 d-flex justify-content-center",children:(0,n.jsx)(b.Z,{page:_,total:K,limit:F,changePage:e=>{en({pageNumber:e})},ellipsis:1})})})}),(0,n.jsx)("br",{}),(0,n.jsx)("br",{}),(0,n.jsx)("br",{}),!k&&m&&(0,n.jsx)(d.Z,{children:(0,n.jsx)(u.Z,{xs:12,children:(0,n.jsx)(c(),{href:"/trashBox/"+m,legacyBehavior:!0,children:(0,n.jsx)(h.Z,{variant:"light",className:"pull-right border-0 shadow-none",children:(0,n.jsx)("i",{className:"fa fa-5x fa-trash","aria-hidden":"true"})})})})})]})}},49614:function(e,r,t){var n=t(94184),a=t.n(n),s=t(67294),l=t(54728),i=t(85893);let c=s.forwardRef((e,r)=>{let{bsPrefix:t,size:n,vertical:s=!1,className:c,role:o="group",as:d="div",...u}=e,h=(0,l.vE)(t,"btn-group"),x=h;return s&&(x="".concat(h,"-vertical")),(0,i.jsx)(d,{...u,ref:r,role:o,className:a()(c,x,n&&"".concat(h,"-").concat(n))})});c.displayName="ButtonGroup",r.Z=c},5401:function(e,r,t){t.d(r,{Z:function(){return y}});var n=t(94184),a=t.n(n),s=t(67294),l=t(54728),i=t(85893);let c=s.forwardRef((e,r)=>{let{className:t,bsPrefix:n,as:s="div",...c}=e;return n=(0,l.vE)(n,"card-body"),(0,i.jsx)(s,{ref:r,className:a()(t,n),...c})});c.displayName="CardBody";let o=s.forwardRef((e,r)=>{let{className:t,bsPrefix:n,as:s="div",...c}=e;return n=(0,l.vE)(n,"card-footer"),(0,i.jsx)(s,{ref:r,className:a()(t,n),...c})});o.displayName="CardFooter";var d=t(54921);let u=s.forwardRef((e,r)=>{let{bsPrefix:t,className:n,as:c="div",...o}=e,u=(0,l.vE)(t,"card-header"),h=(0,s.useMemo)(()=>({cardHeaderBsPrefix:u}),[u]);return(0,i.jsx)(d.Z.Provider,{value:h,children:(0,i.jsx)(c,{ref:r,...o,className:a()(n,u)})})});u.displayName="CardHeader";let h=s.forwardRef((e,r)=>{let{bsPrefix:t,className:n,variant:s,as:c="img",...o}=e,d=(0,l.vE)(t,"card-img");return(0,i.jsx)(c,{ref:r,className:a()(s?"".concat(d,"-").concat(s):d,n),...o})});h.displayName="CardImg";let x=s.forwardRef((e,r)=>{let{className:t,bsPrefix:n,as:s="div",...c}=e;return n=(0,l.vE)(n,"card-img-overlay"),(0,i.jsx)(s,{ref:r,className:a()(t,n),...c})});x.displayName="CardImgOverlay";let f=s.forwardRef((e,r)=>{let{className:t,bsPrefix:n,as:s="a",...c}=e;return n=(0,l.vE)(n,"card-link"),(0,i.jsx)(s,{ref:r,className:a()(t,n),...c})});f.displayName="CardLink";var j=t(68236);let m=(0,j.Z)("h6"),p=s.forwardRef((e,r)=>{let{className:t,bsPrefix:n,as:s=m,...c}=e;return n=(0,l.vE)(n,"card-subtitle"),(0,i.jsx)(s,{ref:r,className:a()(t,n),...c})});p.displayName="CardSubtitle";let g=s.forwardRef((e,r)=>{let{className:t,bsPrefix:n,as:s="p",...c}=e;return n=(0,l.vE)(n,"card-text"),(0,i.jsx)(s,{ref:r,className:a()(t,n),...c})});g.displayName="CardText";let v=(0,j.Z)("h5"),Z=s.forwardRef((e,r)=>{let{className:t,bsPrefix:n,as:s=v,...c}=e;return n=(0,l.vE)(n,"card-title"),(0,i.jsx)(s,{ref:r,className:a()(t,n),...c})});Z.displayName="CardTitle";let b=s.forwardRef((e,r)=>{let{bsPrefix:t,className:n,bg:s,text:o,border:d,body:u=!1,children:h,as:x="div",...f}=e,j=(0,l.vE)(t,"card");return(0,i.jsx)(x,{ref:r,...f,className:a()(n,j,s&&"bg-".concat(s),o&&"text-".concat(o),d&&"border-".concat(d)),children:u?(0,i.jsx)(c,{children:h}):h})});b.displayName="Card";var y=Object.assign(b,{Img:h,Title:Z,Subtitle:p,Body:c,Link:f,Text:g,Header:u,Footer:o,ImgOverlay:x})}}]);