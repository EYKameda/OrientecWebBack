package br.com.orientec.backend.application.port.out.sede;

import br.com.orientec.backend.adapter.out.persistence.entity.sede.Sede;
import br.com.orientec.backend.application.port.out.CrudPersistencePort;

public interface SedePersistencePort extends CrudPersistencePort<Sede, Long> {}
