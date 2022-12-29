<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Psy\Util\Str;

class InventorySeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('inventories')->insert([
            'barley'=>0,
            'hops'=>0,
            'malt'=>0,
            'wheat'=>0,
            'yeast'=>0,
            'maintenance'=>0
        ]);
    }
}
