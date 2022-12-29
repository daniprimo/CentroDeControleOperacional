import './style.css';

export default function Consultar() {
    return (
        <>
            <div style={{
                display: 'flex',
                flexGrow: 'row',
                backgroundColor: '#2f575735',
                width: '96%',
                marginLeft: '2%',
                marginTop: '2%',
                marginBottom: '2%',
                paddingLeft: '2%',
            }}>
                <div style={{
                    display: 'flex',
                    flexDirection: 'column',
                    backgroundColor: 'yellow',
                    marginTop: '2%',
                    marginBottom: '2%',
                    marginRight: '2%',
                    paddingRight: '9%',
                    paddingBottom: "2%",
                    borderRadius: '10px',
                }}>
                    <div
                        style={{
                            display: 'flex',
                            flexGrow: 'row',
                        }}
                    >

                        <div style={{
                            display: 'flex',
                            flexDirection: 'column',
                            marginTop: '7%',
                            marginLeft: '5%',
                        }}>
                            <div><h4>Prefixo:</h4></div>
                            <div><h2>21.578</h2></div>
                        </div>

                        <div style={{
                            display: 'flex',
                            flexDirection: 'column',
                            marginTop: '7%',
                            marginLeft: '10%',
                        }}>
                            <div><h4>Placa:</h4></div>
                            <div><h2>BKW'2462</h2></div>
                        </div>
                    </div>

                    <div style={{
                        display: 'flex',
                        flexGrow: 'row',
                        marginTop: '2%',
                        marginLeft: '5%',
                    }}>
                        <div><h4>Documento:</h4></div>
                        <div style={{
                            marginLeft: '3%'
                        }}><h2>RENAVAN''9879</h2></div>
                    </div>

                </div>

                <div style={{
                    backgroundColor: 'red',
                    marginTop: '2%',
                    marginBottom: '2%',
                    marginRight: '2%'
                }}>
                    <h1> DIV 1</h1>

                </div>

            </div>
        </>
    )
}