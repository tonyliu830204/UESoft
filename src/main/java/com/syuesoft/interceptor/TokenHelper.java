package com.syuesoft.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.LocalizedTextUtil;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import java.math.BigInteger;
import java.util.Map;
import java.util.Random;

public class TokenHelper
{
  public static final String DEFAULT_TOKEN_NAME = "struts.token";
  public static final String TOKEN_NAME_FIELD = "struts.token.name";
  private static final Logger LOG = LoggerFactory.getLogger(TokenHelper.class);
  private static final Random RANDOM = new Random();

  public static String setToken()
  {
    return setToken("struts.token");
  }

  public static String setToken(String tokenName)
  {
    Map session = ActionContext.getContext().getSession();
    String token = generateGUID();
    try {
      session.put(tokenName, token);
    } catch (IllegalStateException e) {
      String msg = "Error creating HttpSession due response is commited to client. You can use the CreateSessionInterceptor or create the HttpSession from your action before the result is rendered to the client: " + e.getMessage();
      LOG.error(msg, e, new String[0]);
      throw new IllegalArgumentException(msg);
    }
    return token;
  }

  public static String getToken()
  {
    return getToken("struts.token");
  }

  public static String getToken(String tokenName)
  {
    if (tokenName == null) {
      return null;
    }
    Map params = ActionContext.getContext().getParameters();
    String[] tokens = (String[])params.get(tokenName);
    if ((tokens == null) || (tokens.length < 1)) {
      LOG.warn("Could not find token mapped to token name " + tokenName, new String[0]);
      return null;
    }
    String token = tokens[0];
    return token;
  }

  public static String getTokenName()
  {
    Map params = ActionContext.getContext().getParameters();
    if (!params.containsKey("struts.token.name")) {
      LOG.warn("Could not find token name in params.", new String[0]);
      return null;
    }
    String[] tokenNames = (String[])params.get("struts.token.name");
    if ((tokenNames == null) || (tokenNames.length < 1)) {
      LOG.warn("Got a null or empty token name.", new String[0]);
      return null;
    }
    String tokenName = tokenNames[0];
    return tokenName;
  }

  public static boolean validToken()
  {
    String tokenName = getTokenName();
    if (tokenName == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("no token name found -> Invalid token ", new String[0]);
      }
      return true;
    }
    String token = getToken(tokenName);
    if (token == null) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("no token found for token name " + tokenName + " -> Invalid token ", new String[0]);
      }
      return false;
    }
    Map session = ActionContext.getContext().getSession();
    String sessionToken = (String)session.get(tokenName);
    if (!token.equals(sessionToken)) {
      LOG.warn(LocalizedTextUtil.findText(TokenHelper.class, "struts.internal.invalid.token", ActionContext.getContext().getLocale(), "Form token {0} does not match the session token {1}.", new Object[] { token, sessionToken }), new String[0]);
      return false;
    }
    session.remove(tokenName);
    return true;
  }

  public static String generateGUID() {
    return new BigInteger(165, RANDOM).toString(36).toUpperCase();
  }
}