import React from 'react';
import '../Global.css'

export default class Footer extends React.Component {
    render() {
        return(
            <>
                <hr className="w-25" style={{ color: "black"}}></hr>
                <footer className="text-center">
                    <p className="text-center mb-3">
                        BandMixer is a platform used to connect musicians, offering a solution<br/>
                        that they can use to create a band, join an existing one, or just find someone<br/>
                        to jam with or learn from.
                    </p>
                    <p>Copyright &copy; BandMixer 2020</p>
                </footer>
            </>
        )
    }
}