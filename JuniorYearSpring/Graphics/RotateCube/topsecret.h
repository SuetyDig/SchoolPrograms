//
//  topsecret.h
//  test
//
//  Created by Adam on 2/26/14.
//  Copyright (c) 2014 Adam. All rights reserved.
//

#ifndef test_topsecret_h
#define test_topsecret_h

#include <OpenGL/OpenGL.h>
#include <GLUT/GLUT.h>
#include "PenroseTriforce.c"

void transform_eye();

void init_mod();

void draw_triangles();

void makeFace(GLfloat face[4][3], GLfloat point0[3], GLfloat point1[3], GLfloat point2[3], GLfloat point3[3]);

GLfloat getEyex();
GLfloat getEyey();
GLfloat getEyez();

#endif
