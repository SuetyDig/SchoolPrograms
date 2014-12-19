//
//  raytracer.h
//  raytracer
//
//  Created by Adam on 4/23/14.
//  Copyright (c) 2014 Adam. All rights reserved.
//

#ifndef raytracer_raytracer_h
#define raytracer_raytracer_h

#include "vector.h"
#include <GLUT/GLUT.h>



//Object Types
typedef struct
{
    vector t1;
    vector t2;
    vector t3;
    vector n;
    double shiny;

} triangle;

//0 is a triangle, 1 is a rectangle, 2 is a cube

//Light types
typedef struct
{
    vector center;
    double radius;
    
} lightSphere;

//Contains all objects in the scene
triangle objects[14];

//Contains all lights in the scene
lightSphere lights[1];

//Variables
vector screen, eye;

//Value for ambient lighting
double ambient;

//Counter that keeps track of the number of objects
int numObjects;

//Counter that keeps track of the number of lights
int numLights;

void draw(double res);

void drawPixel(double x, double y, double r, double g, double b);

int drawRay(vector p1, vector p2, triangle *tri);

int drawRayLight(vector p1, vector p2, lightSphere *sp);

vector getNormal(vector t1, vector t2, vector t3);

double getU(vector p1, vector p2, vector t3, vector normal);

vector getI(vector p1, vector p2, double u);

vector getV(vector t, vector i);

vector getC(vector v1, vector v2);

double getD(vector c1, vector c2);

double getAbsValue(vector p1);

void makeTri(vector p1, vector p2, vector p3, double shiny);

void makeRect(vector p1, vector p2, vector p3, vector p4, double shiny);

void makeCube(vector center, double offset, double shiny);

void makeSphereLight(vector center, double radius);

double getUSphere(vector p1, vector p2, lightSphere *sp);

double getDistanceLight(vector p1, vector p2, lightSphere *light);

double colorPixel(vector p1, vector p2);

double getDiffuse(vector p1, vector p2);

#endif
