import React from 'react';
import { Row, Col } from 'react-bootstrap';

export default class Footer extends React.Component {
    render() {
        return(
            <footer className="text-center text-light bg-dark mt-3 py-1 pt-2">
                <Row>
                    <Col>
                        <strong><u>About</u></strong>
                        <p>BandMixer is a platform used to connect musicians, offering a solution<br/>
                        that they can use to create a band, join an existing one, or just find someone<br/>
                        to jam with or learn from.
                        </p>
                    </Col>
                    <Col>
                        <strong><u>Context</u></strong>
                        <p>For context, this website is created as a part of a semester-long project<br/>
                        for the course CS 309 at Iowa State University. The CY_O1 project team consists<br/>
                        of Andrew Groebe, Timothy Schommer, Aidan Webster, and Aj Sandor</p>
                    </Col>
                </Row>
                <p>Copyright &copy; BandMixer 2020</p>
            </footer>
        )
    }
}