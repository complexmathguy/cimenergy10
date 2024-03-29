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
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.userdefinedmodels.controller.query;

import com.occulue.api.*;
import com.occulue.controller.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * Implements Spring Controller query CQRS processing for entity TurbineGovernorUserDefined.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/TurbineGovernorUserDefined")
public class TurbineGovernorUserDefinedQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a TurbineGovernorUserDefined using a UUID
   *
   * @param UUID turbineGovernorUserDefinedId
   * @return TurbineGovernorUserDefined
   */
  @GetMapping("/load")
  public TurbineGovernorUserDefined load(
      @RequestParam(required = true) UUID turbineGovernorUserDefinedId) {
    TurbineGovernorUserDefined entity = null;

    try {
      entity =
          TurbineGovernorUserDefinedBusinessDelegate.getTurbineGovernorUserDefinedInstance()
              .getTurbineGovernorUserDefined(
                  new TurbineGovernorUserDefinedFetchOneSummary(turbineGovernorUserDefinedId));
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "failed to load TurbineGovernorUserDefined using Id " + turbineGovernorUserDefinedId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all TurbineGovernorUserDefined business objects
   *
   * @return Set<TurbineGovernorUserDefined>
   */
  @GetMapping("/")
  public List<TurbineGovernorUserDefined> loadAll() {
    List<TurbineGovernorUserDefined> turbineGovernorUserDefinedList = null;

    try {
      // load the TurbineGovernorUserDefined
      turbineGovernorUserDefinedList =
          TurbineGovernorUserDefinedBusinessDelegate.getTurbineGovernorUserDefinedInstance()
              .getAllTurbineGovernorUserDefined();

      if (turbineGovernorUserDefinedList != null)
        LOGGER.log(Level.INFO, "successfully loaded all TurbineGovernorUserDefineds");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all TurbineGovernorUserDefineds ", exc);
      return null;
    }

    return turbineGovernorUserDefinedList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected TurbineGovernorUserDefined turbineGovernorUserDefined = null;
  private static final Logger LOGGER =
      Logger.getLogger(TurbineGovernorUserDefinedQueryRestController.class.getName());
}
