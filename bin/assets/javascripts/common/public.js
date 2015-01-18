function addFavorite(a, title, url) {
    url = url || a.href;
    title = title || a.title;
    try{ // IE
        window.external.addFavorite(url, title);
    } catch(error) {
        try{ // Firefox
            window.sidebar.addPanel(title, url, "");
        } catch(e) {
            if (/Opera/.test(window.navigator.userAgent)) { // Opera
                a.rel = "sidebar";
                a.href = url;
                return true;
            }
            alert('加入收藏失败，请使用 Ctrl+D 进行添加');
        }
    }
    return false;
}