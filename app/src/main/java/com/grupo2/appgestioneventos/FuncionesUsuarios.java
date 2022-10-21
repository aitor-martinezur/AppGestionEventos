public class FuncionesUsuarios {
    //Archivo con las funciones para crear, borrar y editar usuarios

    //funcion para crear un usuario nuevo en la base de datos
    /* 
     * @param   usuario el objeto usuario con toda la informacion para meterlo en la base de datos
     * @param   dbr     la referencia a la instancia de la base de datos
     */
    public void crearUsuario(Usuario usuario, DatabaseReference dbr){
        //array con el nombre de los campos para que los meta de forma automatica en la base de datos
        //String[] datos = {"id","nombre","apellido","email",""};

        //carga los usuarios actuales de la base de datos
        ArrayList<Usuario> usuarios = LoginActivity.cargarUsuarios();
        //recoge el id del ultimo user para usar el siguiente
        int ultimoID=0;
        for (int i=0; i<usuarios.size(); i++){
            if (usuarios.get(i).getId()>ultimoID){
                ultimoID = (usuarios.get(i).getId())+1;
            }
        }
        //pasa los datos del usuario a formato Map/HashMap para poder meterlos en la base de datos
        //Map<String, Object> usuarioNuevo = new HashMap<>();

        //pasa el objeto usuario a la base de datos
        dbr.child("usuarios").child("usuario"+String.valueOf(ultimoID)).setValue(usuario);
    }

    //funcion para actualizar un usuario de la base de datos
    /* 
     * @param   usuario el objeto usuario con toda la informacion para meterlo en la base de datos
     * @param   dbr     la referencia a la instancia de la base de datos
     */
    public void actualizarUsuario(Usuario usuario, DatabaseReference dbr){
        //array con el nombre de los campos para que los meta de forma automatica en la base de datos
        //String[] datos = {"id","nombre","apellido","email",""};

        //pasa los datos del usuario a formato Map/HashMap para poder meterlos en la base de datos
        //Map<String, Object> usuarioNuevo = new HashMap<>();

        //pasa el objeto usuario a la base de datos
        dbr.child("usuarios").child("usuario"+String.valueOf(usuario.getId())).setValue(usuario);
    }
}

/*
 * referencia a la instancia de la base de datos
  DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
 * nuevo usuario - 
  mDatabase.child("users").child(userId).setValue(user);
 * actualizar usuario -
   mDatabase.child("users").child(userId).child("username").setValue(name);
 */


/*
    METER DATOS EN LA BASE DE DATOS
    -------------------------------
    INSTANCIA BaseDatos ->
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    RECOGER DATOS ->
    Map<String, Object> user = new HashMap<>();
    user.put("name", user.getText().toString());
    ...

    ENVIAR DATOS ->
    db.collection("coleccion").document("documento").set(datos)
    .addOnSuccessListener(new OnSuccessListener<Void>(){
        @Override
        public void onSuccess(Void aVoid){
        Log.d(TAG, "LLAMADA EXITOSA");
        }
    })
    .addOnFailureListener(new OnFailureListener<Void>(){
        @Override
        public void onFailure(Void aVoid){
        Log.d(TAG, "LLAMADA ERRONEA");
        }
    })

*/
