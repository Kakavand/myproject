    import React, { Component } from 'react';
    import { Button, ButtonGroup, Container, Table } from 'reactstrap';
    import AppNavbar from './AppNavbar';
    import { Link } from 'react-router-dom';

    class ServiceList extends Component {

        constructor(props) {
            super(props);
            this.state = {services: []};
            this.remove = this.remove.bind(this);
        }

        componentDidMount() {
            fetch('/services')
                .then(response => response.json())
                .then(data => this.setState({services: data}));
        }

        async remove(id) {
            await fetch(`/services/${id}`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(() => {
                let updatedServices = [...this.state.services].filter(i => i.id !== id);
                this.setState({services: updatedServices});
            });
        }

        render() {
            const {services} = this.state;

            const serviceList = services.map(service => {
                return <tr key={service.id}>
                    <td style={{whiteSpace: 'nowrap'}}>{service.name}</td>
                    <td>{service.url}</td>
                    <td> 
                        {service.status}
                    </td>
                    <td>
                        {service.creationTime}
                    </td>
                    <td>
                        <ButtonGroup>
                            <Button size="sm" color="primary" tag={Link} to={"/services/" + service.id}>Edit</Button>
                            <Button size="sm" color="danger" onClick={() => this.remove(service.id)}>Delete</Button>
                        </ButtonGroup>
                    </td>
                </tr>
            });

            return (
                <div>
                    <AppNavbar/>
                    <Container fluid>
                        <div className="float-right" style={{ display: 'flex', justifyContent: 'flex-end' }}>
                            <Button color="success" tag={Link} to="/services/new">Add Service</Button>
                        </div>
                        <h3>Services</h3>
                        <Table className="mt-4">
                            <thead>
                            <tr>
                                <th width="20%">Name</th>
                                <th width="20%">URL</th>
                                <th width="20%">Status</th>
                                <th width="20%">Creation Time</th>
                                <th width="20%">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            {serviceList}
                            </tbody>
                        </Table>
                    </Container>
                </div>
            );
        }
    }

    export default ServiceList;