/*
 * Copyright (c) 2013 John I. Moore, Jr. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.softmoore.filter;

import java.io.IOException;
import java.util.*;
import java.util.regex.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Filters out bad user input from HTTP requests to avoid malicious attacks
 * including Cross Site Scripting (XSS), SQL Injection, and HTML Injection
 * vulnerabilities, among others.  This class is adapted from a class of
 * the similar name developed by Jason Brittain for the book <i>Tomcat:
 * The Definitive Guide</i> (Second Edition), O'Reilly Media,
 * ISBN 13: 9780596101060, ISBN 10: 0596101066)
 *
 * @author John Moore
 */
public class BadInputFilter extends com.oreilly.tomcat.filter.BadInputFilter
  {
    /**
     * Construct a new instance of this class with default property values.
     */
    public BadInputFilter()
      {
        super();
      }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain filterChain) throws IOException, ServletException
      {
        // Skip filtering for non-HTTP requests and responses.
        if (!(request instanceof HttpServletRequest)
            || !(response instanceof HttpServletResponse))
          {
            filterChain.doFilter(request, response);
            return;
          }

        // Only let requests through based on the allows and denies.
        if (processAllowsAndDenies(request, response))
          {
            // Filter the input for potentially dangerous JavaScript
            // code so that bad user input is cleaned out of the request
            // by the time Tomcat begins to perform the request.
            FilterableRequest filterableRequest = new FilterableRequest((HttpServletRequest) request);
            filterParameters(filterableRequest);

            // Perform the request.
            filterChain.doFilter(filterableRequest, response);
          }
      }

    /**
     * Filters all existing parameters for potentially dangerous content,
     * and escapes any if they are found.
     *
     * @param request The FilterableRequest that contains the parameters.
     */
    public void filterParameters(FilterableRequest request)
      {
        Map<String, String[]> paramMap = request.getModifiableParameterMap();

        // Loop through each of the substitution patterns.
        for (String patternString : parameterEscapes.keySet())
          {
            Pattern pattern = Pattern.compile(patternString);

            // Loop through the list of parameter names.
            for (String name : paramMap.keySet())
              {
                String[] values = request.getParameterValues(name);

                // See if the name contains the pattern.
                Matcher matcher = pattern.matcher(name);
                if (matcher.find())
                  {
                    // The parameter's name matched a pattern, so we
                    // fix it by modifying the name, adding the parameter
                    // back as the new name, and removing the old one.
                    String newName = matcher.replaceAll((String) parameterEscapes
                        .get(patternString));
                    paramMap.remove(name);
                    paramMap.put(newName, values);
                    servletContext.log("Parameter name " + name
                        + " matched pattern \"" + patternString
                        + "\".  Remote addr: " + ((HttpServletRequest) request).getRemoteAddr());
                  }
              }

            // Loop through the list of parameter values for each name.
            for (String name : paramMap.keySet())
              {
                String[] values = request.getParameterValues(name);
                // Check the parameter's values for the pattern.
                if (values != null)
                  {
                    for (int i = 0;  i < values.length;  ++i)
                      {
                        String value = values[i];
                        Matcher matcher = pattern.matcher(value);
                        if (matcher.find())
                          {
                            // The value matched, so we modify the value
                            // and then set it back into the array.
                            String newValue;
                            newValue = matcher.replaceAll((String) parameterEscapes.get(patternString));
                            values[i] = newValue;
                            servletContext.log("Parameter \"" + name + "\"'s value \""
                                + value + "\" matched pattern \"" + patternString
                                + "\".  Remote addr: " + ((HttpServletRequest) request).getRemoteAddr());
                            servletContext.log("newValue = " + newValue);
                          }
                      }
                  }
              }
          }
      }
  }
