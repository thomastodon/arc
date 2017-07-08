package dialects

import org.hibernate.dialect.MySQL5Dialect

class MySQL5MyISAMDialect : MySQL5Dialect() {

    override fun getTableTypeString() = " ENGINE=MyISAM"

    override fun dropConstraints() = false
}

// Note:
// The older TYPE option was synonymous with ENGINE. TYPE was deprecated in MySQL 4.0 and
// removed in MySQL 5.5. When upgrading to MySQL 5.5 or later, you must convert existing
// applications that rely on TYPE to use ENGINE instead.