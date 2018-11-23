import React, { Component } from "react";
import StickyFooter from 'react-sticky-footer';

class FooterPage extends Component {
render() {
return (
<StickyFooter
    bottomThreshold={50}
    normalStyles={{
    backgroundColor: "#111111",
    padding: "2rem"
    }}
    stickyStyles={{
    backgroundColor: "rgba(255,255,255,.8)",
    padding: "2rem"
    }}
>
    Add any footer markup here
</StickyFooter>
);
}
}

export default FooterPage;