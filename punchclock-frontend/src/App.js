import React, { useEffect, useState, useRef } from 'react';
import Axios from 'axios';
import './App.css';
import {TableCell, TableBody,Table, Button, TableContainer, TableHead, TableRow, Dialog, DialogTitle, DialogContent, TextField, Select} from '@material-ui/core';
import {KeyboardTimePicker,KeyboardDatePicker} from '@material-ui/pickers';
import DeleteIcon from '@material-ui/icons/Delete';
import Paper from '@material-ui/core/Paper';

function App() {
  const checkedinRef = useRef();
  const checkedoutRef = useRef();
  const roomref = useRef();
  const catref = useRef();
  const floorRef = useRef();
  const usageRef = useRef();
  const passwortRef = useRef();
  const usernameRef = useRef();
  const [entries,setEntries] = useState([]);
  const [categories,setCategories] = useState([]);
  const [rooms,setRooms] = useState([]);
  const [request, setRequest] = useState([]);
  const [open, setOpen] = useState(false);
  let api = `http://localhost:8081/`;


  useEffect( () => {
    Axios.get(api + "entries")
        .then(res => { setEntries(res.data)} )
        .catch( err => {console.log(err)});

  }, []);

  useEffect( () => {
    Axios.get(api + "rooms")
        .then(res => { setRooms(res.data)})
        .catch( err => {console.log(err)});
  }, []);

  useEffect( () => {
    Axios.get(api + "categories")
        .then(res => { setCategories(res.data)})
        .catch( err => {console.log(err)});
  }, []);


  const onDeleteEntry = id => {
      Axios.delete(api + "entries/" + {id} )
          .then(res => {setEntries(res.data)})
          .catch( err => {console.log(err)})
  }

  const onDeleteRoom = id => {
    Axios.delete(api + "rooms/" + {id} )
        .then(res => {setRooms(res.data)})
        .catch( err => {console.log(err)})
  }

  const onDeleteCategory = id => {
    Axios.delete(api + "categories/" + {id} )
        .then(res => {setEntries(res.data)})
        .catch( err => {console.log(err)})
  }

  const onEditEntry  = entry => {
    setRequest({
      path: api + "entries/" + entry.id,
      type: "PUT",
      checkIn: entry.checkIn,
      checkOut: entry.checkOut,
      room: entry.room,
      category: entry.category
    });
    setOpen(true);
  }

  const onEditRoom  = room => {
    setRequest({
      path: api + "rooms/" + room.id,
      type: "PUT",
      floor: room.floor,
      usage: room.usage
    });
    setOpen(true);
  }

  const onEditCategory  = cat => {
    setRequest({
      path: api + "categories/" + cat.id,
      type: "PUT",
      category: cat.category
    });
    setOpen(true);
  }

  const onAddEntry  = () => {
    setRequest({
      path: api + "entries",
      type: "POST",
      checkIn: null,
      checkOut: null,
      room: null,
      category: null,
      user: null

    });
    setOpen(true);
  }
  const onAddRoom  = () => {
    setRequest({
      path: api + "rooms/",
      type: "POST",
      floor: 0,
      usage: ""
    });
    setOpen(true);
  }
  const onAddCategory  = () => {
    setRequest({
      path: `${api}`,
      type: "POST",
      category: ""
    });
    setOpen(true);
  }

  const handleClose = () => {
    setOpen(false);
  };
/*
  const handleChange = (event) => {
    setDatabase(event.target.value)
  };
*/
  const handleSubmitEntry = (event) => {
    event.preventDefault();
    Axios({
      method: request.type,
      url: request.path,
      data: {checkIn: checkedinRef.current.value, checkOut: checkedoutRef.current.value, room: roomref.current.value, category: catref.current.value}
    }).then(res => {setEntries(res.data)})
        .catch( err => {console.log(err)});
    handleClose();
  }

  const handleSubmitCategory= (event) => {
    event.preventDefault();
    Axios({
      method: request.type,
      url: request.path,
      data: {category: catref.current.value}
    }).then(res => {setCategories(res.data)})
        .catch( err => {console.log(err)});
    handleClose();
  }

  const handleSubmitRoom = (event) => {
    event.preventDefault();
    Axios({
      method: request.type,
      url: request.path,
      data: {floor: floorRef.current.value, usage: usageRef.current.value}
    }).then(res => {setRooms(res.data)})
        .catch( err => {console.log(err)});
    handleClose();
  }

  return (
      <div className="App">
        <Button
            color="primary"
            onClick={() => onAddEntry()}>
          Add new Entry
        </Button>
        <Dialog open={open} onClose={handleClose} aria-labelledby="newEntry">
          <DialogTitle id="new Entry">Edit/Add Entry</DialogTitle>
          <DialogContent>
            <form onSubmit={handleSubmitEntry}>
              <TextField
                  id="checkIn"
                  label="Check In"
                  type="datetime-local"
                  defaultValue="YYYY-MM-DDTHH:MM"
                  InputLabelProps={{
                    shrink: true,
                  }}
                  inputRef={checkedinRef}
              />
              <TextField
                  id="checkOut"
                  label="Check Out"
                  type="datetime-local"
                  defaultValue="YYYY-MM-DDTHH:MM"
                  InputLabelProps={{
                    shrink: true,
                  }}
                  inputRef={checkedoutRef}
              />
              <select>
                  {rooms.map((e) => (
                      <option value={e.usage}>{e.usage} </option>
                  ))}
              </select>
              <select>
                {categories.map((e) => (
                    <option value={e.category}>{e.category} </option>
                ))}
              </select>
              <div>
                <Button onClick={handleClose}>
                  Cancel
                </Button>
                <Button color="primary" type="submit" label="submit">
                  Submit
                </Button>
              </div>
            </form>
          </DialogContent>
        </Dialog>
        Entries
        <TableContainer component={Paper}>
          <Table aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Id</TableCell>
                <TableCell>Check In</TableCell>
                <TableCell>Chceck Out</TableCell>
                <TableCell>Room</TableCell>
                <TableCell>Category</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {entries.map((entry) => (
                  <TableRow key={entry.id}>
                    <TableCell component="th" scope="row">
                      {entry.id}
                    </TableCell>
                    <TableCell>{entry.checkIn}</TableCell>
                    <TableCell>{entry.checkOut}</TableCell>
                    <TableCell>{entry.room}</TableCell>
                    <TableCell>{entry.category}</TableCell>
                    <TableCell align="right">
                      <Button variant="contained"
                              color="primary"
                              onClick={() => onEditEntry(entry)}>
                        Edit
                      </Button>
                      <Button variant="contained"
                              color="secondary"
                              onClick={() => onDeleteEntry(entry.id)}
                              startIcon={<DeleteIcon />}>
                        Delete
                      </Button>
                    </TableCell>
                  </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>

        Categories
        <TableContainer component={Paper}>
          <Table aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Id</TableCell>
                <TableCell>Category</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {categories.map((cat) => (
                  <TableRow key={cat.id}>
                    <TableCell component="th" scope="row">
                      {cat.id}
                    </TableCell>
                    <TableCell>{cat.category}</TableCell>
                    <TableCell align="right">
                      <Button variant="contained"
                              color="primary"
                              onClick={() => onEditCategory(cat)}>
                        Edit
                      </Button>
                      <Button variant="contained"
                              color="secondary"
                              onClick={() => onDeleteCategory(cat.id)}
                              startIcon={<DeleteIcon />}>
                        Delete
                      </Button>
                    </TableCell>
                  </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>

        Rooms
        <TableContainer component={Paper}>
          <Table aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Id</TableCell>
                <TableCell>floor</TableCell>
                <TableCell>usage</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {rooms.map((room) => (
                  <TableRow key={room.id}>
                    <TableCell component="th" scope="row">
                      {room.id}
                    </TableCell>
                    <TableCell>{room.floor}</TableCell>
                    <TableCell>{room.usage}</TableCell>
                    <TableCell align="right">
                      <Button variant="contained"
                              color="primary"
                              onClick={() => onEditRoom(room)}>
                        Edit
                      </Button>
                      <Button variant="contained"
                              color="secondary"
                              onClick={() => onDeleteRoom(room.id)}
                              startIcon={<DeleteIcon />}>
                        Delete
                      </Button>
                    </TableCell>
                  </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </div>


  );
}

export default App;