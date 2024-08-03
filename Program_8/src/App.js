import React, { Component } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import InputGroup from 'react-bootstrap/InputGroup';
import FormControl from 'react-bootstrap/FormControl';
import ListGroup from 'react-bootstrap/ListGroup';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userInput: "",
      list: []
    };
  }

  // Set a user input value
  updateInput = (value) => {
    this.setState({
      userInput: value,
    });
  }

  // Add item if user input is not empty
  addItem = () => {
    if (this.state.userInput !== '') {
      const userInput = {
        id: Math.random(),
        value: this.state.userInput
      };
      const list = [...this.state.list];
      list.push(userInput);
      this.setState({
        list,
        userInput: ""
      });
    }
  }

  // Function to delete item from list using id
  deleteItem = (key) => {
    const list = [...this.state.list];
    const updateList = list.filter(item => item.id !== key);
    this.setState({
      list: updateList,
    });
  }

  render() {
    return (
      <Container>
        <Row style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          fontSize: '3rem',
          fontWeight: 'bolder',
        }}
        >
          TODO LIST
        </Row>
        <hr />
        <Row>
          <Col md={{ span: 5, offset: 4 }}>
            <InputGroup className="mb-3">
              <FormControl
                placeholder="add item . . . "
                size="lg"
                value={this.state.userInput}
                onChange={(item) => this.updateInput(item.target.value)}
                aria-label="add something"
                aria-describedby="basic-addon2"
              />
              <Button
                variant="dark"
                size="lg"
                onClick={this.addItem}
              >
                ADD
              </Button>
            </InputGroup>
          </Col>
        </Row>
        <Row>
          <Col md={{ span: 5, offset: 4 }}>
            <ListGroup>
              {this.state.list.map(item => (
                <ListGroup.Item
                  key={item.id}
                  variant="dark"
                  action
                  onClick={() => this.deleteItem(item.id)}
                >
                  {item.value}
                </ListGroup.Item>
              ))}
            </ListGroup>
          </Col>
        </Row>
      </Container>
    );
  }
}

export default App;
