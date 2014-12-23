<?php

namespace spec\RomanNumeral;

use PhpSpec\ObjectBehavior;
use Prophecy\Argument;

class EncoderSpec extends ObjectBehavior
{
    function it_is_initializable()
    {
        $this->shouldHaveType('RomanNumeral\Encoder');
    }

    function it_encodes_a_sequence_of_i()
    {
        $this->encode(1)->shouldReturn('I');
        $this->encode(2)->shouldReturn('II');
        $this->encode(3)->shouldReturn('III');
    }

    function it_encodes_basic_symbols_to_numerals()
    {
        $this->encode(5)->shouldReturn('V');
        $this->encode(5)->shouldReturn('V');
        $this->encode(10)->shouldReturn('X');
        $this->encode(50)->shouldReturn('L');
        $this->encode(100)->shouldReturn('C');
        $this->encode(500)->shouldReturn('D');
        $this->encode(1000)->shouldReturn('M');
    }

    function it_encodes_mix()
    {
        $this->encode(4)->shouldReturn('IV');
        $this->encode(7)->shouldReturn('VII');
        $this->encode(222)->shouldReturn('CCXXII');
        $this->encode(356)->shouldReturn('CCCLVI');
        $this->encode(789)->shouldReturn('DCCLXXXIX');
        $this->encode(999)->shouldReturn('CMXCIX');
        $this->encode(1337)->shouldReturn('MCCCXXXVII');
        $this->encode(3333)->shouldReturn('MMMCCCXXXIII');
        $this->encode(4555)->shouldReturn('MMMMDLV');
    }
}
