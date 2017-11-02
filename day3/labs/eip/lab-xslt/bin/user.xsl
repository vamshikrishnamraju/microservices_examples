<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <body>
        <h2>User Details</h2>
        <table border="1">
          <tr bgcolor="#9acd32">
            <th>FirstName</th>
            <th>LastName</th>
            <th>Job</th>
            <th>Address</th>
          </tr>
          <xsl:for-each select="users/user">
            <tr>
              <td>
                <xsl:value-of select="fname" />
              </td>
              <td>
                <xsl:value-of select="lname" />
              </td>
              <td>
                <xsl:value-of select="job" />
              </td>
              <td>
                <xsl:value-of select="address" />
              </td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>