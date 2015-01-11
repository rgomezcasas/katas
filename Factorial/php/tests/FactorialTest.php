<?php

class FactorialTest extends PHPUnit_Framework_TestCase
{
    /** @test */
    public function itShouldCalculateTheFactorialOfZero()
    {
        $this->assertSame(1, Factorial::of(0));
    }

    /** @test */
    public function itShouldCalculateTheFactorialOfOne()
    {
        $this->assertSame(1, Factorial::of(1));
    }

    /** @test */
    public function itShouldCalculateTheFactorialOfIntegers()
    {
        $this->assertSame(6, Factorial::of(3));
        $this->assertSame(120, Factorial::of(5));
        $this->assertSame(3628800, Factorial::of(10));
        $this->assertSame(15511210043330985984000000, Factorial::of(25));
    }
}
