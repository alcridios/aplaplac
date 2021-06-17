import React, { useEffect, useState } from 'react';
import API from '../../services/API';
import { Button } from 'react-bootstrap';
import AlumnoList from '../../components/Alumno/AlumnoList'
import AlumnoModal from '../../components/Alumno/AlumnoModal';

const Alumno = props => {

  const [Alumnos, setAlumnos] = useState([]);

  const initialAlumnoData = {
    nombre: '',
    puntos: 0,
    entrenador: {
      id: -1
    }
  }

  const [ganancias, setGanancias] = useState([]);

  const [newAlumnoData, setNewAlumnoData] = useState(initialAlumnoData);
  const [listaEntrenadores, setListaEntrenadores] = useState([]);
  

  const [showModal, setShowModal] = useState(false);
  const [isEdit, setIsEdit] = useState(false);
  const [validateForm, setValidateForm] = useState(false);
  const [errorMsg, setErrorMsg] = useState('');

  useEffect(() => {
    getAlumnos();

    //Para cargar la info en los select del modal
    getEntrenadores();
    getGanancias();
  }, []);

  const getAlumnos = async () => {
    try {
      let data = await API.get('/alumnos');
      setAlumnos(data);
      getGanancias();
    }
    catch (error) {
      console.log(error);
    }
  }

  const getEntrenadores = async () => {
    try {
      let response = await API.get('/entrenadores');
      setListaEntrenadores(response);
    }
    catch (error) {
      setErrorMsg(JSON.stringify(error));
    }
  }

  const borrarAlumno = async (id) => {
    if (window.confirm("Estas seguro?")) {
      try {
        await API.remove(`/alumnos/${id}`);
        getAlumnos();
      }
      catch (error) {
        console.log(error);
      }
    }
  }

  const getGanancias = async() => {
    try {
      let response = await API.get(`/alumnos/calculoGanancias`);
      setGanancias(response);
      
      //console.log(ganancias);
    }
    catch (error) {
      console.log(error);
    }
  }

  const recalcularRanking = async (id) => {
    try {
      await API.update(`/alumnos/${id}/actions/recalculateRanking`);
      getAlumnos();
    }
    catch (error) {
      console.log(error);
    }
  }

  const agregarAlumno = async () => {
    try {
      if (newAlumnoData.entrenador.id === -1) {
        const nuevoAlumno = {
          nombre: `${newAlumnoData.nombre}`,
          puntos: `${newAlumnoData.puntos}`
        }
        await API.save('/alumnos', nuevoAlumno);
      } else {
        await API.save('/alumnos', newAlumnoData);
      }
      resetModal();
      getAlumnos();
    }
    catch (error) {
      setErrorMsg(JSON.stringify(error));
    }
  }

  const editarAlumno = async (id) => {
    try {
      await API.update(`/alumnos/${id}`, newAlumnoData);
      resetModal();
      getAlumnos();
    }
    catch (error) {
      setErrorMsg(JSON.stringify(error));
    }
  }

  const handleFormChange = (tipo, value) => {
    if (value === '')
      setValidateForm(true);

    //Copia los valores de 'newEntrenadorData' en un nuevo objeto 'data'
    let data = Object.assign({}, newAlumnoData);

    switch (tipo) {
      case 'id':
        data.id = parseInt(value);
        break;
      case 'nombre':
        data.nombre = value;
        break;
      case 'direccion':
        data.direccion = value;
        break;
      case 'entrenador':
        data.entrenador = value;
        break;
      default:
        break;
    }

    setNewAlumnoData(data);
  }

  const handleFormSubmit = (form, isEdit) => {
    setValidateForm(true);

    if (form.checkValidity())
      isEdit ? editarAlumno(newAlumnoData.id) : agregarAlumno();
  };

  const resetModal = () => {
    setShowModal(false);
    setIsEdit(false);
    setNewAlumnoData(initialAlumnoData);
    setValidateForm(false);
    setErrorMsg('');
  }

  const handleOpenModal = (editar = false, AlumnoToEdit = null) => {
    if (editar) {
      setIsEdit(true);
      setNewAlumnoData(AlumnoToEdit);
    }
    setShowModal(true);
  }

  const handleCloseModal = () => {
    resetModal();
  }

  return (
    <div className="container mt-4">
      <h1>Alumnos</h1>
      <Button variant="info mb-3" onClick={() => handleOpenModal()}> Agregar Alumno </Button>
      <AlumnoModal
        show={showModal}
        handleClose={handleCloseModal}
        handleChange={handleFormChange}
        handleSubmit={handleFormSubmit}
        isEdit={isEdit}
        validate={validateForm}
        errorMsg={errorMsg}
        Alumno={newAlumnoData}
        listaEntrenadores={listaEntrenadores}
      />
      <AlumnoList
        Alumnos={Alumnos}
        borrarAlumno={borrarAlumno}
        editarAlumno={handleOpenModal}
        recalcularRanking={recalcularRanking}
        ganancias={ganancias}
      />
    </div>
  );

}

export default Alumno;