//
//  vector.c
//  raytracer
//
//  Created by Adam on 4/23/14.
//  Copyright (c) 2014 Adam. All rights reserved.
//

#include "vector.h"

double dotProduct(vector p1, vector p2)
{
    return (p1.x * p2.x) + (p1.y * p2.y) + (p1.z * p2.z);
}

vector crossProduct(vector p1, vector p2)
{
    vector new;
    new.x = ((p1.y*p2.z) - (p1.z*p2.y));
    new.y = ((p1.z*p2.x) - (p1.x*p2.z));
    new.z = ((p1.x*p2.y) - (p1.y*p2.x));
    
    return new;
}

vector vectSub(vector p1, vector p2)
{
    vector new;
    new.x = p1.x - p2.x;
    new.y = p1.y - p2.y;
    new.z = p1.z - p2.z;
    
    return new;
}

vector vectAdd(vector p1, vector p2)
{
    vector new;
    new.x = p1.x + p2.x;
    new.y = p1.y + p2.y;
    new.z = p1.z + p2.z;
    
    return new;
}