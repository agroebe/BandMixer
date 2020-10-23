import React from 'react';
import { Image } from 'react-bootstrap';

export default class About extends React.Component {
    render() {
        return(
            <>
                <div className="text-center">
                    <h3>Page not found...</h3>
                    <a href="/" className="text-center">Navigate home?</a>
                </div>
                <Image className="rounded mx-auto d-block" src="https://image.freepik.com/free-vector/404-error-with-character-error-design-template-website_114341-24.jpg"></Image>
            </>
        )
    }
}