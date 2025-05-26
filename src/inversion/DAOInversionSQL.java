package inversion;

import utilidades.DAOManager;

public class DAOInversionSQL {

    public boolean insertar(Inversion inversion, DAOManager daoManager){
        String sql="INSERT INTO inversion VALUES ('" +
                inversion.getInversor() + "','"
                + inversion


    }
    public boolean eliminar(int idInversion, DAOManager daoManager){



    }
    public boolean update(Inversion inversion, DAOManager daoManager){



    }
    public boolean read(int idInversion, DAOManager daoManager){



    }
    public boolean readAll(DAOManager daoManager){



    }
}
