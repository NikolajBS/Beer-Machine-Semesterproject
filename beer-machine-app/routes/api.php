<?php

use App\Http\Controllers\APIController;
use App\Http\Controllers\ProductController;
use App\Http\Resources\BatchResource;
use App\Models\Batch;
use App\Models\Temperature;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::post('posttest',[APIController::class,'resourceFunction']);
Route::get('testing',[APIController::class,'resourceFunction']);

Route::get('endpoint', [APIController::class,'startCmd']);
