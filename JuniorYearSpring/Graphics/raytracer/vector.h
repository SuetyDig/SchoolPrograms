//
//  vector.h
//  raytracer
//
//  Created by Adam on 4/23/14.
//  Copyright (c) 2014 Adam. All rights reserved.
//

#ifndef raytracer_vector_h
#define raytracer_vector_h

typedef struct
{
    double x;
    double y;
    double z;
} vector;

double dotProduct(vector p1, vector p2);

vector crossProduct(vector p1, vector p2);

vector vectSub(vector p1, vector p2);

vector vectAdd(vector p1, vector p2);

#endif
