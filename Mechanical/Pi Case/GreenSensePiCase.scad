$fn=64;
//Greenhouse Raspberry Pi case
difference(){
    union(){
        xdim2 = 103;
        ydim2 = 68;
        zdim2 = 43;
        //main cube
        translate([-50.5, -ydim2/2, 0])
        {
            cube([xdim2,ydim2,zdim2]);
        }
        //wire channel cylinder
        rotate([90, 0, 0])
        translate([-50.5, 15, -32])
        {
            cylinder(22, 15, 15);
        }
        //wire channel straight
        translate([-65.5, 10, 0])
        {
            cube([15, 22, 15]);
        }
    }
    
    xdim1 = 88;
    ydim1 = 60;
    zdim1 = 43;
    //center/middle cut out
    translate([-45.5, -ydim1/2, 2])
    {
        cube([xdim1,ydim1,zdim1]);
    }
    
    //bottom inside y hollow
    translate([-xdim1/2-1.5, -41.5/2 , 0])
    {
        cube([xdim1, 41.5, 3]);
    }
    //bottom inside x hollow
    translate([-35.25, -ydim1/2 , 0])
    {
        cube([50.5, 60, 3]);
    } 
    //bottom inside right hollow
    translate([22.75, -ydim1/2 , 0])
    {
        cube([19.75, 60, 3]);
    }
    
    //wire channel cylinder hollow
    rotate([90, 0, 0])
    translate([-50.5, 15, -30])
    {
        cylinder(18, 13, 13);
    }
    //wire channel top hollow
    translate([-50.5, 12, 13])
    {
        cube([32, 18, 15]);
    }  
    //wire channel bottom hollow
    translate([-63.5, 12, 0])
    {
        cube([14, 18, 15]);
    }
    //wire channel flatten leftover curve
    translate([-50.5, 12, 2])
    {
        cube([6, 18, 3]);
    }
    
    //usb/eth side indent
    translate([47.5, -ydim1/2, 0])
    {
        cube([6, ydim1, zdim1]);
    }
    //usb/eth sliding cover plate channel
    translate([47.5, -32, 0])
    {
        cube([3.1, 64, 41]);
    }
    //cutout for usb and eth
    translate([42.5, -ydim1/2, 0])
    {
        cube([8,ydim1,20]);
    }
    
    //cutout for sd card
    translate([-51.5, -9, 0])
    {
        cube([8,18,5]);
    }
    
    //cutout for power and dc jack
    translate([-38, -34, 2])
    {
        cube([13, 6, 27]);
    }   
    //cutout for video and audio
    translate([-22.5, -34, 2])
    {
       cube([38, 6, 8]);
    }
    
    //cap screw holes
    radius = 1.25;
    //hole in Q4
    translate([-48,25,32])
    {
        cylinder (zdim1,radius,radius);
    }
    //hole in Q3
    translate([-48,-25,32])
    {
        cylinder (zdim1,radius,radius);
    }
    //hole in Q2
    translate([45,-25,32])
    {
        cylinder (zdim1,radius,radius);
    }
    //hole in Q1
    translate([45,25,32])
    {
        cylinder (zdim1,radius,radius);
    }
    
    //alightment pin holes
    radius1 = 1.05;
    //hole in Q4
    translate([-48,20,36])
    {
        cylinder (zdim1,radius1,radius1);
    }
    //hole in Q3
    translate([-48,-20,36])
    {
        cylinder (zdim1,radius1,radius1);
    }
    //hole in Q2
    translate([45,-20,36])
    {
        cylinder (zdim1,radius1,radius1);
    }
    //hole in Q1
    translate([45,20,36])
    {
        cylinder (zdim1,radius1,radius1);
    }
    
    //rpi holes
    rad = 1.35;
    //hole in Q4
    translate([-39,24.5,0])
    {
        cylinder (3,rad,rad);
    }
    //hole in Q3
    translate([-39,-24.5,0])
    {
        cylinder (3,rad,rad);
    }
    //hole in Q2
    translate([19,-24.5,0])
    {
        cylinder (3,rad,rad);
    }
    //hole in Q1
    translate([19,24.5,0])
    {
        cylinder (3,rad,rad);
    }
   
}