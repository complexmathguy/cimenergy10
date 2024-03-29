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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentboundaryprofile.wires.delegate;

import com.occulue.api.*;
import com.occulue.com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentboundaryprofile.wires.validator.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryUpdateEmitter;

/**
 * Junction business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of Junction related services in the case of a Junction business
 *       related service failing.
 *   <li>Exposes a simpler, uniform Junction interface to the business tier, making it easy for
 *       clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill Junction business related
 *       services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class JunctionBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public JunctionBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * Junction Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return JunctionBusinessDelegate
   */
  public static JunctionBusinessDelegate getJunctionInstance() {
    return (new JunctionBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createJunction(CreateJunctionCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getJunctionId() == null) command.setJunctionId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      JunctionValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateJunctionCommand - by convention the future return value for a create
      // command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateJunctionCommand of Junction is " + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create Junction - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateJunctionCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateJunction(UpdateJunctionCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      JunctionValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateJunctionCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save Junction - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteJunctionCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteJunctionCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      JunctionValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteJunctionCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to delete Junction using Id = " + command.getJunctionId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the Junction via JunctionFetchOneSummary
   *
   * @param summary JunctionFetchOneSummary
   * @return JunctionFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public Junction getJunction(JunctionFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("JunctionFetchOneSummary arg cannot be null");

    Junction entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      JunctionValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a Junction
      // --------------------------------------
      CompletableFuture<Junction> futureEntity =
          queryGateway.query(
              new FindJunctionQuery(new LoadJunctionFilter(summary.getJunctionId())),
              ResponseTypes.instanceOf(Junction.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg = "Unable to locate Junction with id " + summary.getJunctionId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all Junctions
   *
   * @return List<Junction>
   * @exception ProcessingException Thrown if any problems
   */
  public List<Junction> getAllJunction() throws ProcessingException {
    List<Junction> list = null;

    try {
      CompletableFuture<List<Junction>> futureList =
          queryGateway.query(
              new FindAllJunctionQuery(), ResponseTypes.multipleInstancesOf(Junction.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all Junction";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return Junction
   */
  private Junction load(UUID id) throws ProcessingException {
    junction =
        JunctionBusinessDelegate.getJunctionInstance().getJunction(new JunctionFetchOneSummary(id));
    return junction;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private Junction junction = null;
  private static final Logger LOGGER = Logger.getLogger(JunctionBusinessDelegate.class.getName());
}
