<%@ page import="Model.Taglia.Taglia" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,carousel,products"/>
        <jsp:param name="customerScripts" value="carousel,products,myAccount"/>
        <jsp:param name="customerAjaxScripts" value="productPage"/>
        <jsp:param name="title" value="T&#x000F8;j - products details"/>
    </jsp:include>
</head>
<body>
<!--headerTop-->
<%@include file="../partials/customer/headerTop.jsp"%>
<hr class="border2-hr">

<!--middle-->
<jsp:include page="../partials/customer/titleBanner.jsp">
    <jsp:param name="title" value="Dettagli Prodotto"/>
</jsp:include>

<%Articolo articolo = (Articolo) request.getAttribute("articolo");
List<Articolo> articoliColor = (List<Articolo>) request.getAttribute("filterColor");
%>
<div class="container-top">
    <div class="row">
        <div class="column38">
            <div class="product-details">
                <div class="img-zoom-container">
                    <img class="big-img" src="${pageContext.request.contextPath}/covers/<%=articolo.getPaths().get(0).getPathName()%>" alt="<%=articolo.getNome()%>" data="<%=articolo.getSesso()%>">
                    <div class="row">
                        <div class="column100">
                            <div class="owl-slider">
                                <div class="carousel owl-carousel">
                                    <%for(int i = 0; i < articolo.getPaths().size(); i++){%>
                                    <div class="item">
                                        <div class="single-product">
                                            <a href="javascript:void(0)">
                                                <img id="small-img<%=i%>" class="image-first small-img" src="${pageContext.request.contextPath}/covers/<%=articolo.getPaths().get(i).getPathName()%>" alt="<%=articolo.getNome()%>">
                                            </a>
                                        </div>
                                    </div>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="column62">
            <div class="product_d_right">
                <form action="${pageContext.request.contextPath}/customers/cart">
                    <h1><%=articolo.getNome()%></h1>
                    <div class="product_price">
                        <span class="current_price">€ <%=articolo.getPrezzo()%></span>
                    </div>
                    <div class="product_desc">
                        <p><%=articolo.getDescrizione()%></p>
                    </div>
                    <div class="product_variant color">
                        <h3>colore</h3>
                        <select onclick="callAjax()" name="produc-color" id="selectColor">
                            <option selected value="default">Scegli opzione</option>
                            <%for (int i = 0; i < articoliColor.size(); i++){%>
                            <option value="<%=articoliColor.get(i).getIDarticolo()%>">
                                <%for (int j = 0; j <articoliColor.get(i).getColori().size(); j++){%>
                                <%=articoliColor.get(i).getColori().get(j).getNome()%>
                            </option>
                                <%}%>
                            <%}%>
                        </select>
                    </div>
                    <div class="product_variant size">
                        <h3>taglia</h3>
                        <select name="products-size">
                            <option selected value="default">Scegli opzione</option>
                            <%int j = 0; for (Taglia taglia: articolo.getTaglie()){%>
                            <option id="option_taglia<%=j%>" value="<%=taglia.getId_nome()%>" onclick="setQuantita(<%=taglia.getQuantita()%>)"><%=taglia.getId_nome()%></option>
                            <%j++; }%>
                        </select>
                    </div>
                    <div class="product_variant quantity">
                        <label>quantità</label>
                        <input id="input_quantita" min="1"  value="1" type="number">
                        <button class="btn-add-cart" type="submit">Aggiungi al carrello</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="product_d_info">
    <div class="container-top">
        <div class="row">
            <div class="column100">
                <div class="product_d_inner">
                    <div id="dashboard-Account">
                        <button class="btn-Account active-btn" onclick="showhide('info')">Info</button>
                        <button class="btn-Account" onclick="showhide('sheet')">Scheda Tecnica</button>
                    </div>
                    <div class="tab-content">
                        <div class="account-content" id="info">
                            <div class="product_info_content">
                                <p>Fashion has been creating well-designed collections since 2010. The brand offers
                                    feminine designs delivering stylish separates and statement dresses which have
                                    since evolved into a full ready-to-wear collection in which every item is a vital
                                    part of a woman's wardrobe. The result? Cool, easy, chic looks with youthful elegance
                                    and unmistakable signature style. All the beautiful pieces are made in Italy and
                                    manufactured with the greatest attention. Now Fashion extends to a range of accessories
                                    including shoes, hats, belts and more!
                                </p>
                            </div>
                        </div>
                        <div class="account-content" id="sheet">
                            <div class="product_d_table">
                                <form action="#">
                                    <table>
                                        <tbody>
                                        <tr>
                                            <td class="first_child">Compositions</td>
                                            <td>Polyester</td>
                                        </tr>
                                        <tr>
                                            <td class="first_child">Styles</td>
                                            <td>Girly</td>
                                        </tr>
                                        <tr>
                                            <td class="first_child">Properties</td>
                                            <td>Short Dress</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                            <div class="product_info_content">
                                <p>Fashion has been creating well-designed collections since 2010. The brand offers feminine
                                    designs delivering stylish separates and statement dresses which have since evolved into
                                    a full ready-to-wear collection in which every item is a vital part of a woman's wardrobe.
                                    The result? Cool, easy, chic looks with youthful elegance and unmistakable signature style.
                                    All the beautiful pieces are made in Italy and manufactured with the greatest attention.
                                    Now Fashion extends to a range of accessories including shoes, hats, belts and more!
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container-top">
    <div class="new-arrival">
        <h3>I trend del momento su tøj</h3>
    </div>
    <%@include file="../partials/customer/carousel.jsp"%>
</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>