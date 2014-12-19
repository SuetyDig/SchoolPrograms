//
//  raytracer.c
//  raytracer
//
//  Created by Adam on 4/23/14.
//  Copyright (c) 2014 Adam. All rights reserved.
//

//Assumes square resolution
#include "raytracer.h"

int numObjects = 0;
int numLights = 0;
double gRes;

void drawPixel(double x,double y,double r,double g,double b) // assume x,y 0-500
{
double  SZ =  2 / gRes;
	glBegin(GL_TRIANGLES);
    glColor3f(r,g,b);
    glVertex2f(-1.0+SZ*(double)x,     -1.0+SZ*(double)y);
    glVertex2f(-1.0+SZ*(double)x,     -1.0+SZ*(double)y+ SZ);
    glVertex2f(-1.0+SZ*(double)x+ SZ, -1.0+SZ*(double)y);
    
    glVertex2f(-1.0+SZ*(double)x+ SZ, -1.0+SZ*(double)y);
    glVertex2f(-1.0+SZ*(double)x+ SZ, -1.0+SZ*(double)y+ SZ);
    glVertex2f(-1.0+SZ*(double)x,     -1.0+SZ*(double)y+ SZ);
	glEnd();
}

void draw(double res)
{
    gRes = res;
    double inc = 1 / res;
    double x,y, brightness;
    //double os1, os2, os3, os4;
    glClear(GL_COLOR_BUFFER_BIT);
    for(x = 0; x <= 1; x += inc) // x <= is relative to screen size
    {
        for (y = 0; y <= 1; y += inc) // y <= is relative to screen size
        {
            //Set the screen location
            screen.x = x;
            screen.y = y;
            screen.z = 0;
            
            /*Oversampling stuff
             //Samples based off of 4 subpixels
             vector holder = screen;
             
             holder.x -= (.5/100);
             holder.y -= (.5/100);
             if (drawRay(eye, holder, t1, t2, t3, normal) != 0) os1 = 1;
             else os1 = 0;
             
             holder = screen;
             holder.x += (.5/100);
             holder.y -= (.5/100);
             if (drawRay(eye, holder, t1, t2, t3, normal) != 0) os2 = 1;
             else os2 = 0;
             
             holder = screen;
             holder.x -= (.5/100);
             holder.y += (.5/100);
             if (drawRay(eye, holder, t1, t2, t3, normal) != 0) os3 = 1;
             else os3 = 0;
             
             holder = screen;
             holder.x += (.5/100);
             holder.y += (.5/100);
             if (drawRay(eye, holder, t1, t2, t3, normal) != 0) os4 = 1;
             else os4 = 0;
             
             double os = (os1 + os2 + os3 +os4) / 4;
             
             drawpixel(x, y, os, os, os);
             */
            
            brightness = colorPixel(eye, screen);
            if(brightness > 1 ) brightness = 1;
            drawPixel(x*500, y*500, brightness, brightness, brightness);
            //            drawPixel(125, 80, 1, 0, 0);
            
            //Testintg stuff delete later
            //            if(drawRayRect(eye, screen, &objects[0].sq)) drawPixel(x, y, 0, 0, 1);
            //            if (drawRayCube(eye, screen, &objects[1].c)) drawPixel(x,y,1,0,0);
        }
    }

}


int drawRay(vector p1, vector p2, triangle *tri)
{
    double u;
        
    u = getU(p1, p2, tri->t3, tri->n);
        
    if (u < 0) return 0;
    
    vector i = getI(p1, p2, u);
    
    vector v1, v2, v3;
    
    
    //Gets the new vectors
    v1 = getV(tri->t1, i);
    v2 = getV(tri->t2, i);
    v3 = getV(tri->t3, i);
    
    vector c1, c2, c3;
    
    c1 = getC(v1, v2);
    c2 = getC(v2, v3);
    c3 = getC(v3, v1);
    
    double d1, d2, d3;
    
    d1 = getD(c1, c2);
    d2 = getD(c2, c3);
    d3 = getD(c3, c1);

    if (d1 >= 0 && d2 >= 0 && d3 >= 0) return 1;
    else return 0;
}

double getU(vector p1, vector p2, vector t3, vector normal)
{
    return (dotProduct(normal, vectSub(t3, p1))) / (dotProduct(normal, vectSub(p2, p1)));
}

vector getNormal(vector t1, vector t2, vector t3)
{
    return (crossProduct((vectSub(t2, t1)), vectSub(t3, t1)));
}

vector getI(vector p1, vector p2, double u)
{
    vector new;
    
    new = vectSub(p2, p1);
    new.x = new.x*u;
    new.y = new.y*u;
    new.z = new.z*u;
    
    return vectAdd(p1, new);
}

vector getV(vector t, vector i)
{
    return vectSub(t, i);
}

vector getC(vector v1, vector v2)
{
    return crossProduct(v1, v2);
}

double getD(vector c1, vector c2)
{
    return dotProduct(c1, c2);
}

double getAbsValue(vector p1)
{
    return sqrt((p1.x*p1.x) + (p1.y*p1.y) + (p1.z*p1.z));
}

void makeTri(vector p1, vector p2, vector p3, double shiny)
{
    triangle new;
    
    new.shiny = shiny;
    
    //Makes the first point of the triangle
    new.t1 = p1;
    new.t2 = p2;
    new.t3 = p3;
    
    new.n = getNormal(new.t1, new.t2, new.t3);
    
    objects[numObjects] = new;
    numObjects++;

}

void makeRect(vector p1, vector p2, vector p3, vector p4, double shiny)
{
    makeTri(p2, p4, p3, shiny);
    makeTri(p1, p4, p2, shiny);
}

void makeCube(vector center, double offset, double shiny)
{
    //Creates points of the triangle based off of the center and offset
    vector p1 = { center.x - offset, center.y - offset, center.z - offset};
    vector p2 = { center.x + offset, center.y - offset, center.z - offset};
    vector p3 = { center.x + offset, center.y - offset, center.z + offset};
    vector p4 = { center.x - offset, center.y - offset, center.z + offset};
    vector p5 = { center.x - offset, center.y + offset, center.z + offset};
    vector p6 = { center.x - offset, center.y + offset, center.z - offset};
    vector p7 = { center.x + offset, center.y + offset, center.z - offset};
    vector p8 = { center.x + offset, center.y + offset, center.z + offset};
    
    makeRect(p1, p2, p3, p4, shiny); //Makes bottom face
    makeRect(p1, p2, p7, p6, shiny); //Makes front face
    makeRect(p1, p4, p5, p6, shiny); //Makes left face
    makeRect(p2, p3, p8, p7, shiny); //Makes right face
    makeRect(p3, p4, p5, p8, shiny); //Makes back face
    makeRect(p6, p7, p8, p5, shiny); //Makes top face
}

void makeSphereLight(vector center, double radius)
{
    lightSphere new;
    new.center.x = center.x;
    new.center.y = center.y;
    new.center.z = center.z;
    
    new.radius = radius;
        
    lights[numLights] = new;
    numLights++;
}

double getUSphere(vector p1, vector p2, lightSphere *sp)
{
    return ((sp->center.x-p1.x)*(p2.x-p1.x) + (sp->center.y-p1.y)*(p2.y-p1.y) + (sp->center.z-p1.z)*(p2.z-p1.z)) /
    (getAbsValue(vectSub(p2, p1))*getAbsValue(vectSub(p2, p1)));
}

double getDistanceLight(vector p1, vector p2, lightSphere *light)
{
    double u = getUSphere(p1, p2, light);
    
    vector p = getI(p1, p2, u);
    
    vector temp = vectSub(p, light->center);
    
    return getAbsValue(temp);
}

int drawRayLight(vector p1, vector p2, lightSphere *sp)
{
    double dist = getDistanceLight(p1, p2, sp);
    
    if(dist <= sp->radius) return 1;
    return 0;
}

double colorPixel(vector p1, vector p2)
{
    double diffuse = getDiffuse(p1, p2);
    
    return diffuse + ambient;
}

//Calculates The diffuse contribution of lighting
double getDiffuse(vector p1, vector p2)
{
    double bright = 0;
    double uObj = -1;
    double uLight = -1;
    int objLoc = 0;
    int lightLoc = 0;
    int i;
    
    //Find lowest positive U value finding it first for objects then for light
    for (i = 0; i < numObjects; i++)
    {
        if(drawRay(p1, p2, &objects[i]))
        {
            double u = getU(p1, p2, objects[i].t3, objects[i].n);
            if((uObj > u || uObj == -1) && u > 0)
            {
                uObj = u;
                objLoc = i;
            }
        }
    }
    
    for (i = 0; i < numLights; i++)
    {
        if (drawRayLight(p1, p2, &lights[i]))
        {
            double u = getUSphere(p1, p2, &lights[i]);
            if((uLight > u || uLight == -1) && u > 0)
            {
                uLight = u;
                lightLoc = i;
            }
        }
    }
    
    //Figures out brightness
    if (uLight > 0 && (uLight < uObj || uObj < 0)) bright = 1;
    if (uObj > 0 && (uObj < uLight || uLight < 0))
    {
        for(i = 0; i < numLights; i++)
        {
            vector intersection = getI(p1, p2, uObj);
                        
            if(!(p2.x == lights[i].center.x && p2.y == lights[i].center.y && p2.z == lights[i].center.z))
            {
                bright += (getDiffuse(intersection, lights[i].center)) /
                getAbsValue(vectSub(lights[i].center, intersection));
            }
        }
    }
    return bright;
}