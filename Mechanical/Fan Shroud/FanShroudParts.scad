$fn=64;
//fan shroud case parts
difference(){
    //main cube bottom section
    union(){
        xdim2 = 80;
        ydim2 = 80;
        zdim2 = 15;
        translate([-40, -ydim2/2, 0])
        {
            cube([xdim2,ydim2,zdim2]);
        }
        translate([40, -3, 0])
        {
            cube([23, 25, 15]);
        }
    }
    
    xdim1 = 51;
    ydim1 = 51;
    zdim1 = 13;
    //center cut out
    translate([-xdim1/2, -ydim1/2, 0])
    {
        cube([xdim1,ydim1,zdim1]);
    }
    //top trim down
    translate([-40, -40, 8])
    {
        cube([69.5,82,12]);
    }
    //side cutout 1
    translate([-40, 29.5, 4])
    {
        cube([80,11.5,10]);
    }
    //side cutout 2
    translate([-40, -40, 4])
    {
        cube([80,10.5,10]);
    }
    //side cutout 3
    translate([-40, -40, 4])
    {
        cube([10.5,80,10]);
    }
    //side cutout 4a
    translate([29.5, -40, 4])
    {
        cube([10.5,37,16]);
    }
    //side cutout 4b
    translate([29.5, 22, 4])
    {
        cube([10.5,20,16]);
    }
    
    //servo cutouta
    translate([34.5, -3, 3])
    {
        cube([23,30,16]);
    }
    //servo cutoutb
    translate([29.5, -3, 3])
    {
        cube([36,12,16]);
    }
    //servo slot
    translate([29.5, 14.875, 3])
    {
        cube([35.5,2.75,16]);
    }
    //servo hole1
    rotate([90, 0, 0])
    translate([32.1,9,-30])
    {    
            cylinder(60, 1.3, 1.3);
        
    }
    //servo hole2
    rotate([90, 0, 0])
    translate([60,9,-30])
    {   
            cylinder(60, 1.3, 1.3);
        
    }
    
    
    radius = 2.1;
    //mount hole1
    translate([-33.5,33.5,0])
    {
        cylinder(5,radius,radius);
    }
    //countersink1
    translate([-33.5,33.5,3])
    {
        cylinder(3,radius,5);
    }
    //mount hole2
    translate([33.5,-33.5,0])
    {
        cylinder(5,radius,radius);
    }
    //countersink2
    translate([33.5,-33.5,3])
    {
        cylinder(3,radius,5);
    }
    //mount hole3
    translate([-33.5,-33.5,0])
    {
        cylinder(5,radius,radius);
    }
    //countersink3
    translate([-33.5,-33.5,3])
    {
        cylinder(3,radius,5);
    }
    //mount hole4
    translate([33.5,33.5,0])
    {
        cylinder(5,radius,radius);
    }
    //countersink4
    translate([33.5,33.5,3])
    {
        cylinder(3,radius,5);
    }
    
    //top shutter holes
    translate([22.5,0,8])
    {
        rotate([90, 0, 0])
            cylinder(60, 1.75, 1.75, true); 
    }
    //top shutter indent
    translate([9.4,20,12])
    {
        rotate([90, 0, 0])
            cylinder(30, 2, 2, true);   
    }
    //middle shutter holes
    translate([6,0,8])
    {
        rotate([90, 0, 0])
            cylinder(60, 1.75, 1.75, true);
        
    }
    //middle shutter indent
    translate([-7.1,20,12])
    {
        rotate([90, 0, 0])
            cylinder(30, 2, 2, true);      
    }
    //bottom shutter holes
    translate([-10.25,0,8])
    {
        rotate([90, 0, 0])
            cylinder(60, 1.75, 1.75, true);
        
    }
    //bottom shutter indent
    translate([-23.4,20,12])
    {
        rotate([90, 0, 0])
            cylinder(30, 2, 2, true);
        
    }
    
    //pin hole 1
    translate([27.5,27.5,6])
    {
        cylinder(2.3, 1.1, 1.1); 
    }
    //pin hole 2
    translate([27.5,-27.5,6])
    {
        cylinder(2.3, 1.1, 1.1); 
    } 
    //pin hole 3
    translate([-27.5,27.5,6])
    {
        cylinder(2.3, 1.1, 1.1); 
    } 
    //pin hole 4
    translate([-27.5,-27.5,6])
    {
        cylinder(2.3, 1.1, 1.1); 
    } 
    
}

//top section with alignment pins
rotate([180, 0, 0])
translate([0, 80, -4])
difference(){
    union(){
        xdim2 = 59;
        ydim2 = 59;
        zdim2 = 4;
        translate([-xdim2/2, -ydim2/2, 0])
        {
            cube([xdim2,ydim2,zdim2]);
        }
        //pin 1
        translate([27.5,27.5,-2])
        {
            cylinder(2, 1, 1); 
        }
        //pin 2
        translate([27.5,-27.5,-2])
        {
            cylinder(2, 1, 1); 
        } 
        //pin 3
        translate([-27.5,27.5,-2])
        {
            cylinder(2, 1, 1); 
        } 
        //pin 4
        translate([-27.5,-27.5,-2])
        {
            cylinder(2, 1, 1); 
        } 
    }

    xdim1 = 51;
    ydim1 = 51;
    zdim1 = 13;
    //center cut out
    translate([-xdim1/2, -ydim1/2, 0])
    {
        cube([xdim1,ydim1,zdim1]);
    }
    
        //top shutter holes
    translate([22.5,0,0])
    {
        rotate([90, 0, 0])
            cylinder(60, 1.75, 1.75, true); 
    }
    //top shutter indent
    translate([9.4,20,4])
    {
        rotate([90, 0, 0])
            cylinder(30, 2, 2, true);   
    }
    //middle shutter holes
    translate([6,0,0])
    {
        rotate([90, 0, 0])
            cylinder(60, 1.75, 1.75, true);
        
    }
    //middle shutter indent
    translate([-7.1,20,4])
    {
        rotate([90, 0, 0])
            cylinder(30, 2, 2, true);      
    }
    //bottom shutter holes
    translate([-10.25,0,0])
    {
        rotate([90, 0, 0])
            cylinder(60, 1.75, 1.75, true);
        
    }
    //bottom shutter indent
    translate([-23.4,20,4])
    {
        rotate([90, 0, 0])
            cylinder(30, 2, 2, true);
        
    } 
}