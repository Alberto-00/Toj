<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="customerStyles" value="otherPage,carousel,products"/>
        <jsp:param name="customerScripts" value="carousel,products,myAccount"/>
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

<div class="container-top">
    <div class="row">
        <div class="column38">
            <div class="product-details">
                <div class="img-zoom-container">
                    <img class="big-img" src="${pageContext.request.contextPath}/images/boy.jpg" alt="product">
                    <div class="row">
                        <div class="column100">
                            <div class="owl-slider">
                                <div class="carousel owl-carousel">
                                    <%for(int i = 0; i < 8; i++){%>
                                    <div class="item">
                                        <div class="single-product">
                                            <a href="javascript:void(0)">
                                                <img class="image-first small-img" src="${pageContext.request.contextPath}/images/woman.jpg" alt="">
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
                <form action="#">
                    <h1>Amazon Cloud Cam</h1>
                    <div class="product_price">
                        <span class="current_price">$70.00</span>
                    </div>
                    <div class="product_desc">
                        <p>More room to move. With 80GB or 160GB of storage and up to 40 hours of battery life, the new iPod classic lets you enjoy up to 40,000 songs or up to 200 hours of video or any combination wherever you go. Cover Flow. Browse through your music collection by flipping through album art. Select an album to turn it over and see the track list. Enhanced interface. Experience a whole new way to browse and view your music and video. Sleeker design. Beautiful, durable, and sleeker than ever, iPod classic now features an anodized aluminum and polish.. </p>
                    </div>
                    <div class="product_variant color">
                        <h3>colore</h3>
                        <select name="produc-color">
                            <option selected value="1">choose in option</option>
                            <option value="2">choose in option2</option>
                            <option value="3">choose in option3</option>
                            <option value="4">choose in option4</option>
                        </select>
                    </div>
                    <div class="product_variant size">
                        <h3>taglia</h3>
                        <select name="products-size">
                            <option selected value="default">size</option>
                            <option value="x">x</option>
                            <option value="xl">xl</option>
                            <option value="">md</option>
                            <option value="4">xxl</option>
                            <option value="4">s</option>
                        </select>
                    </div>
                    <div class="product_variant quantity">
                        <label>quantità</label>
                        <input min="1" max="100" value="1" type="number">
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
    <!--trasformare in import dinamico-->
</div>

<!-- footer-->
<%@include file="../partials/customer/footer.jsp"%>
</body>
</html>