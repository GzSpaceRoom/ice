// **********************************************************************
//
// Copyright (c) 2003
// ZeroC, Inc.
// Billerica, MA, USA
//
// All Rights Reserved.
//
// Ice is free software; you can redistribute it and/or modify it under
// the terms of the GNU General Public License version 2 as published by
// the Free Software Foundation.
//
// **********************************************************************

#ifndef FREEZE_GRAMMAR_UTIL_H
#define FREEZE_GRAMMAR_UTIL_H

#include <Transform/Node.h>
#include <Transform/Parser.h>

namespace Transform
{

extern DataFactoryPtr parseDataFactory;
extern ErrorReporterPtr parseErrorReporter;
extern NodePtr parseResult;
extern int parseLine;

int getInput(char*, int);

class StringTok;
class IdentifierTok;
class BoolTok;
class IntegerTok;
class FloatingTok;

typedef ::IceUtil::Handle<StringTok> StringTokPtr;
typedef ::IceUtil::Handle<IdentifierTok> IdentifierTokPtr;
typedef ::IceUtil::Handle<BoolTok> BoolTokPtr;
typedef ::IceUtil::Handle<IntegerTok> IntegerTokPtr;
typedef ::IceUtil::Handle<FloatingTok> FloatingTokPtr;

// ----------------------------------------------------------------------
// Token
// ----------------------------------------------------------------------

class Token : public Node
{
public:

    Token() { }
    virtual DataPtr evaluate(SymbolTable&) { return 0; }
    virtual void print(std::ostream&) const {}
};

// ----------------------------------------------------------------------
// StringTok
// ----------------------------------------------------------------------

class StringTok : public Token
{
public:

    StringTok() { }

    std::string v;
};

// ----------------------------------------------------------------------
// IdentifierTok
// ----------------------------------------------------------------------

class IdentifierTok : public Token
{
public:

    IdentifierTok() { }
    Identifier v;
};

// ----------------------------------------------------------------------
// BoolTok
// ----------------------------------------------------------------------

class BoolTok : public Token
{
public:

    BoolTok() { }
    bool v;
};

// ----------------------------------------------------------------------
// IntegerTok
// ----------------------------------------------------------------------

class IntegerTok : public Token
{
public:

    IntegerTok() { }
    IceUtil::Int64 v;
};

// ----------------------------------------------------------------------
// FloatingTok
// ----------------------------------------------------------------------

class FloatingTok : public Token
{
public:

    FloatingTok() { }
    double v;
};

} // End of namespace Transform

//
// Stuff for flex and bison
//

#define YYSTYPE Transform::NodePtr
#define YY_DECL int transform_lex(YYSTYPE* yylvalp)
YY_DECL;
int transform_parse();

//
// I must set the initial stack depth to the maximum stack depth to
// disable bison stack resizing. The bison stack resizing routines use
// simple malloc/alloc/memcpy calls, which do not work for the
// YYSTYPE, since YYSTYPE is a C++ type, with constructor, destructor,
// assignment operator, etc.
//
#define YYMAXDEPTH  20000 // 20000 should suffice. Bison default is 10000 as maximum.
#define YYINITDEPTH YYMAXDEPTH // Initial depth is set to max depth, for the reasons described above.

//
// Newer bison versions allow to disable stack resizing by defining
// yyoverflow.
//
#define yyoverflow(a, b, c, d, e, f) transform_error(a)

#endif
