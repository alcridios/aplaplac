import React from 'react'
import { Route, Switch } from 'react-router-dom';
import Alumno from '../containers/Alumno';


export default function Routes() {
  
  return (
    <Switch>
      <Route path="/alumnos" component={Alumno} exact />
    </Switch>
  )
}
