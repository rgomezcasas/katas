<?php

use RomanNumeral\Encoder;

class EncoderTest extends PHPUnit_Framework_TestCase
{
    /**
     * @var RomanNumeral\Encoder
     */
    private $encoder;


    /** @test */
    function setUp()
    {
        $this->encoder = new Encoder;
    }

    /** @test */
    function it_encodes_a_sequence_of_i()
    {
        $this->assertSame('I', $this->encoder->encode(1));
        $this->assertSame('II', $this->encoder->encode(2));
        $this->assertSame('III', $this->encoder->encode(3));
    }

    /** @test */
    function it_encodes_basic_symbols_to_numerals()
    {
        $this->assertSame('V', $this->encoder->encode(5));
        $this->assertSame('V', $this->encoder->encode(5));
        $this->assertSame('X', $this->encoder->encode(10));
        $this->assertSame('L', $this->encoder->encode(50));
        $this->assertSame('C', $this->encoder->encode(100));
        $this->assertSame('D', $this->encoder->encode(500));
        $this->assertSame('M', $this->encoder->encode(1000));
    }

    /** @test */
    function it_encodes_mix()
    {
        $this->assertSame('IV', $this->encoder->encode(4));
        $this->assertSame('VII', $this->encoder->encode(7));
        $this->assertSame('CCXXII', $this->encoder->encode(222));
        $this->assertSame('CCCLVI', $this->encoder->encode(356));
        $this->assertSame('DCCLXXXIX', $this->encoder->encode(789));
        $this->assertSame('CMXCIX', $this->encoder->encode(999));
        $this->assertSame('MCCCXXXVII', $this->encoder->encode(1337));
        $this->assertSame('MMMCCCXXXIII', $this->encoder->encode(3333));
        $this->assertSame('MMMMDLV', $this->encoder->encode(4555));
    }
}
