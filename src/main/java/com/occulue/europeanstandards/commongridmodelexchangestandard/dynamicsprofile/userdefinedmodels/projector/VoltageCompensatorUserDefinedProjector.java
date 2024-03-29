/**
 * ***************************************************************************** Turnstone Biologics
 * Confidential
 *
 * <p>2018 Turnstone Biologics All Rights Reserved.
 *
 * <p>This file is subject to the terms and conditions defined in file 'license.txt', which is part
 * of this source code package.
 *
 * <p>Contributors : Turnstone Biologics - General Release
 * ****************************************************************************
 */
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.userdefinedmodels.projector;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.userdefinedmodels.repository.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.logging.Logger;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Projector for VoltageCompensatorUserDefined as outlined for the CQRS pattern. All event handling
 * and query handling related to VoltageCompensatorUserDefined are invoked here and dispersed as an
 * event to be handled elsewhere.
 *
 * <p>Commands are handled by VoltageCompensatorUserDefinedAggregate
 *
 * @author your_name_here
 */
// @ProcessingGroup("voltageCompensatorUserDefined")
@Component("voltageCompensatorUserDefined-projector")
public class VoltageCompensatorUserDefinedProjector
    extends VoltageCompensatorUserDefinedEntityProjector {

  // core constructor
  public VoltageCompensatorUserDefinedProjector(
      VoltageCompensatorUserDefinedRepository repository, QueryUpdateEmitter queryUpdateEmitter) {
    super(repository);
    this.queryUpdateEmitter = queryUpdateEmitter;
  }

  /*
   * @param	event CreateVoltageCompensatorUserDefinedEvent
   */
  @EventHandler(payloadType = CreateVoltageCompensatorUserDefinedEvent.class)
  public void handle(CreateVoltageCompensatorUserDefinedEvent event) {
    LOGGER.info("handling CreateVoltageCompensatorUserDefinedEvent - " + event);
    VoltageCompensatorUserDefined entity = new VoltageCompensatorUserDefined();
    entity.setVoltageCompensatorUserDefinedId(event.getVoltageCompensatorUserDefinedId());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    create(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVoltageCompensatorUserDefined(entity);
  }

  /*
   * @param	event UpdateVoltageCompensatorUserDefinedEvent
   */
  @EventHandler(payloadType = UpdateVoltageCompensatorUserDefinedEvent.class)
  public void handle(UpdateVoltageCompensatorUserDefinedEvent event) {
    LOGGER.info("handling UpdateVoltageCompensatorUserDefinedEvent - " + event);

    VoltageCompensatorUserDefined entity = new VoltageCompensatorUserDefined();
    entity.setVoltageCompensatorUserDefinedId(event.getVoltageCompensatorUserDefinedId());
    entity.setProprietary(event.getProprietary());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    update(entity);

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVoltageCompensatorUserDefined(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVoltageCompensatorUserDefined(entity);
  }

  /*
   * @param	event DeleteVoltageCompensatorUserDefinedEvent
   */
  @EventHandler(payloadType = DeleteVoltageCompensatorUserDefinedEvent.class)
  public void handle(DeleteVoltageCompensatorUserDefinedEvent event) {
    LOGGER.info("handling DeleteVoltageCompensatorUserDefinedEvent - " + event);

    // ------------------------------------------
    // delete delegation
    // ------------------------------------------
    VoltageCompensatorUserDefined entity = delete(event.getVoltageCompensatorUserDefinedId());

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVoltageCompensatorUserDefined(entity);
  }

  /*
   * @param	event AssignProprietaryToVoltageCompensatorUserDefinedEvent
   */
  @EventHandler(payloadType = AssignProprietaryToVoltageCompensatorUserDefinedEvent.class)
  public void handle(AssignProprietaryToVoltageCompensatorUserDefinedEvent event) {
    LOGGER.info("handling AssignProprietaryToVoltageCompensatorUserDefinedEvent - " + event);

    // ------------------------------------------
    // delegate to assignTo
    // ------------------------------------------
    VoltageCompensatorUserDefined entity =
        assignProprietary(event.getVoltageCompensatorUserDefinedId(), event.getAssignment());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVoltageCompensatorUserDefined(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVoltageCompensatorUserDefined(entity);
  }

  /*
   * @param	event UnAssignProprietaryFromVoltageCompensatorUserDefinedEvent
   */
  @EventHandler(payloadType = UnAssignProprietaryFromVoltageCompensatorUserDefinedEvent.class)
  public void handle(UnAssignProprietaryFromVoltageCompensatorUserDefinedEvent event) {
    LOGGER.info("handling UnAssignProprietaryFromVoltageCompensatorUserDefinedEvent - " + event);

    // ------------------------------------------
    // delegate to unAssignFrom
    // ------------------------------------------
    VoltageCompensatorUserDefined entity =
        unAssignProprietary(event.getVoltageCompensatorUserDefinedId());

    // ------------------------------------------
    // emit to subscribers that find one
    // ------------------------------------------
    emitFindVoltageCompensatorUserDefined(entity);

    // ------------------------------------------
    // emit to subscribers that find all
    // ------------------------------------------
    emitFindAllVoltageCompensatorUserDefined(entity);
  }

  /**
   * Method to retrieve the VoltageCompensatorUserDefined via an
   * VoltageCompensatorUserDefinedPrimaryKey.
   *
   * @param id Long
   * @return VoltageCompensatorUserDefined
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  @SuppressWarnings("unused")
  @QueryHandler
  public VoltageCompensatorUserDefined handle(FindVoltageCompensatorUserDefinedQuery query)
      throws ProcessingException, IllegalArgumentException {
    return find(query.getFilter().getVoltageCompensatorUserDefinedId());
  }

  /**
   * Method to retrieve a collection of all VoltageCompensatorUserDefineds
   *
   * @param query FindAllVoltageCompensatorUserDefinedQuery
   * @return List<VoltageCompensatorUserDefined>
   * @exception ProcessingException Thrown if any problems
   */
  @SuppressWarnings("unused")
  @QueryHandler
  public List<VoltageCompensatorUserDefined> handle(FindAllVoltageCompensatorUserDefinedQuery query)
      throws ProcessingException {
    return findAll(query);
  }

  /**
   * emit to subscription queries of type FindVoltageCompensatorUserDefined, but only if the id
   * matches
   *
   * @param entity VoltageCompensatorUserDefined
   */
  protected void emitFindVoltageCompensatorUserDefined(VoltageCompensatorUserDefined entity) {
    LOGGER.info("handling emitFindVoltageCompensatorUserDefined");

    queryUpdateEmitter.emit(
        FindVoltageCompensatorUserDefinedQuery.class,
        query ->
            query
                .getFilter()
                .getVoltageCompensatorUserDefinedId()
                .equals(entity.getVoltageCompensatorUserDefinedId()),
        entity);
  }

  /**
   * unconditionally emit to subscription queries of type FindAllVoltageCompensatorUserDefined
   *
   * @param entity VoltageCompensatorUserDefined
   */
  protected void emitFindAllVoltageCompensatorUserDefined(VoltageCompensatorUserDefined entity) {
    LOGGER.info("handling emitFindAllVoltageCompensatorUserDefined");

    queryUpdateEmitter.emit(FindAllVoltageCompensatorUserDefinedQuery.class, query -> true, entity);
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired private final QueryUpdateEmitter queryUpdateEmitter;
  private static final Logger LOGGER =
      Logger.getLogger(VoltageCompensatorUserDefinedProjector.class.getName());
}
